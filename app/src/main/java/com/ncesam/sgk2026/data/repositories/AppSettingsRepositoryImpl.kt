package com.ncesam.sgk2026.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ncesam.sgk2026.domain.repositories.AppSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AppSettingsRepositoryImpl(private val dataStore: DataStore<Preferences>) :
	AppSettingsRepository {
	private companion object {
		val TOKEN_KEY: Preferences.Key<String> = stringPreferencesKey("auth_token")
		val USER_ID_KEY: Preferences.Key<String> = stringPreferencesKey("user_id")
		val USER_PINCODE: Preferences.Key<Int> = intPreferencesKey("user_pincode")
	}

	override suspend fun saveToken(token: String) {
		dataStore.edit { preferences -> preferences[TOKEN_KEY] = token }
	}

	override val tokenFlow: Flow<String?> = dataStore.data.map { value ->
		value[TOKEN_KEY]
	}

	override suspend fun saveUserId(userId: String) {
		dataStore.edit { preferences -> preferences[USER_ID_KEY] = userId }
	}

	override val userIdFlow: Flow<String?> =
		dataStore.data.map { preferences -> preferences[USER_ID_KEY] }


	override suspend fun savePinCode(pinCode: Int) {
		dataStore.edit { preferences -> preferences[USER_PINCODE] = pinCode }
	}

	override val pinCodeFlow: Flow<Int?> =
		dataStore.data.map { preferences -> preferences[USER_PINCODE] }

	override suspend fun clearSession() {
		dataStore.edit { preferences -> preferences.clear() }
	}
}