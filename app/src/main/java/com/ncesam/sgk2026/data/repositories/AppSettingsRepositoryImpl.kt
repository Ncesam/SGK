package com.ncesam.sgk2026.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.ncesam.sgk2026.domain.repositories.AppSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AppSettingsRepositoryImpl(private val dataStore: DataStore<Preferences> ) : AppSettingsRepository {
	private companion object {
		val TOKEN_KEY = stringPreferencesKey("auth_token")
		val USER_ID_KEY = stringPreferencesKey("user_id")
	}
	override suspend fun saveToken(token: String) {
		dataStore.edit { preferences -> preferences[TOKEN_KEY] = token }
	}

	override val tokenFlow: Flow<String?> = dataStore.data.map {
		value -> value[TOKEN_KEY]
	}

	override suspend fun clearSession() {
		dataStore.edit {preferences -> preferences.clear()}
	}
}