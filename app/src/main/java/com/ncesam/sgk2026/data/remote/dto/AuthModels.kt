package com.ncesam.sgk2026.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginRequest(
	val email: String,
	val password: String
)

@Serializable
data class AuthLoginResponse(
	val token: String,
	val record: UserDto
)