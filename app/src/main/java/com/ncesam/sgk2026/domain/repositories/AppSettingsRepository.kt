package com.ncesam.sgk2026.domain.repositories

import kotlinx.coroutines.flow.Flow

interface AppSettingsRepository {
	suspend fun saveToken(token: String)
	val tokenFlow: Flow<String?>

	suspend fun saveUserId(userId: String)

	val userIdFlow: Flow<String?>

	suspend fun savePinCode(pinCode: Int)

	val pinCodeFlow: Flow<Int?>

	suspend fun clearSession()

}