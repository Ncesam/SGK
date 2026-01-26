package com.ncesam.sgk2026.domain.repositories

import kotlinx.coroutines.flow.Flow

interface AppSettingsRepository {
	suspend fun saveToken(token: String)
	val tokenFlow: Flow<String?>
	suspend fun clearSession()
}