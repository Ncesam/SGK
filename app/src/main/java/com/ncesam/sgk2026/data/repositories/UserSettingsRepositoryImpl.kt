package com.ncesam.sgk2026.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ncesam.sgk2026.domain.repositories.UserSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserSettingsRepositoryImpl(private val dataStore: DataStore<Preferences>) :
	UserSettingsRepository {
	private companion object {
		val USER_ID_KEY: Preferences.Key<String> = stringPreferencesKey("user_id")
		val USER_PINCODE: Preferences.Key<String> = stringPreferencesKey("user_pincode")
		val USER_USE_BIOMETRIC: Preferences.Key<Boolean> = booleanPreferencesKey("use_use_biometric")
	}



	override suspend fun saveUserId(userId: String) {
		dataStore.edit { preferences -> preferences[USER_ID_KEY] = userId }
	}

	override val userIdFlow: Flow<String?> =
		dataStore.data.map { preferences -> preferences[USER_ID_KEY] }


	override val useBiometricFlow: Flow<Boolean?> = dataStore.data.map {preferences -> preferences[USER_USE_BIOMETRIC]}

	override suspend fun saveUseBiometric(use: Boolean) {
		dataStore.edit { preferences -> preferences[USER_USE_BIOMETRIC] = use}
	}

	override suspend fun savePinCode(pinCode: String) {
		dataStore.edit { preferences -> preferences[USER_PINCODE] = pinCode }
	}

	override val pinCodeFlow: Flow<String?> =
		dataStore.data.map { preferences -> preferences[USER_PINCODE] }

	override suspend fun clearSession() {
		dataStore.edit { preferences -> preferences.clear() }
	}
}