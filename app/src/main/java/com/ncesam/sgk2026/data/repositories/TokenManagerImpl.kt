package com.ncesam.sgk2026.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ncesam.sgk2026.data.remote.AuthApi
import com.ncesam.sgk2026.data.remote.utils.safeApiCall
import com.ncesam.sgk2026.domain.repositories.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.json.JSONObject
import java.util.Base64

class TokenManagerImpl(
	private val dataStore: DataStore<Preferences>,
	private val authApi: AuthApi
) : TokenManager {
	private companion object {
		val TOKEN_KEY: Preferences.Key<String> = stringPreferencesKey("auth_token")
	}

	override suspend fun refreshToken() {
		val token = tokenFlow.first()
		if (token.isNullOrBlank()) {
			throw IllegalArgumentException("Token are empty")
		}
		safeApiCall(
			call = {
				authApi.refreshToken(token)
			},
			mapper = { response ->
				response.token
			}
		).map { value -> saveToken(value) }
	}

	override suspend fun getValidToken(): String? {
		val token = tokenFlow.first()
		if (!token.isNullOrBlank()) {
			if (!isExpiredToken(token)) {
				return token
			} else {
				refreshToken()
			}
		}
		return null
	}

	override fun isExpiredToken(token: String): Boolean {
		return try {
			val parts = token.split(".")
			if (parts.size < 2) return true

			val payloadJson = String(
				Base64.getUrlDecoder().decode(parts[1])
			)
			val payload = JSONObject(payloadJson)
			val exp = payload.getLong("exp")

			val nowSeconds = System.currentTimeMillis() / 1000

			nowSeconds >= (exp - 60L)
		} catch (e: Exception) {
			true
		}
	}

	override suspend fun saveToken(value: String) {
		dataStore.edit { preferences -> preferences[TOKEN_KEY] = value }
	}

	override val tokenFlow: Flow<String?> = dataStore.data.map { value ->
		value[TOKEN_KEY]
	}
}