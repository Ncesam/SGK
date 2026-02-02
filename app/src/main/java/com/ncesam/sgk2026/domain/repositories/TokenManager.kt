package com.ncesam.sgk2026.domain.repositories

import kotlinx.coroutines.flow.Flow


interface TokenManager {

	suspend fun refreshToken()
	suspend fun saveToken(value: String)
	fun isExpiredToken(token: String): Boolean
	suspend fun getValidToken(): String?
	val tokenFlow: Flow<String?>
}