package com.ncesam.sgk2026.data.remote

import com.ncesam.sgk2026.data.remote.dto.AuthLoginRequest
import com.ncesam.sgk2026.data.remote.dto.AuthLoginResponse
import com.ncesam.sgk2026.data.remote.dto.AuthRefreshTokenResponse
import com.ncesam.sgk2026.data.remote.dto.UserDto
import com.ncesam.sgk2026.domain.models.Booking
import com.ncesam.sgk2026.domain.states.RegistrationParams
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
	@POST("/api/collections/users/auth-with-password")
	suspend fun login(
		@Body request: AuthLoginRequest
	): Response<AuthLoginResponse>


	@POST("/api/collections/users/auth-refresh")
	suspend fun refreshToken(
		@Header("Authorization") token: String
	): Response<AuthRefreshTokenResponse>

	@POST("/api/collections/users/records")
	suspend fun register(
		@Body body: RegistrationParams
	): Response<UserDto>
}
