package com.ncesam.sgk2026.data.local.models

data class AppSettings(
	val authToken: String?,
	val userId: String?
) {
	val isLogged = !authToken.isNullOrBlank()
}