package com.ncesam.sgk2026.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginRequest(
	val identity: String,
	val password: String
)

@Serializable
data class AuthLoginResponse(
	val token: String,
	val record: UserDto
)

@Serializable
data class AuthRefreshTokenResponse(
	val token: String,
	val record: UserDto
)