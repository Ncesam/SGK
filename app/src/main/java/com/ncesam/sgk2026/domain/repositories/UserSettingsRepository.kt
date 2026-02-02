package com.ncesam.sgk2026.domain.repositories

import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {
	suspend fun saveUserId(userId: String)
	val userIdFlow: Flow<String?>
	suspend fun savePinCode(pinCode: String)
	val pinCodeFlow: Flow<String?>
	suspend fun saveUseBiometric(use: Boolean)
	val useBiometricFlow: Flow<Boolean?>
	suspend fun clearSession()
}