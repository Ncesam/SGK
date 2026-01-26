package com.ncesam.sgk2026.data.remote

import com.ncesam.sgk2026.data.remote.dto.AuthLoginRequest
import com.ncesam.sgk2026.data.remote.dto.AuthLoginResponse
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
	@GET("/api/collections/users/auth-with-password")
	suspend fun login(
		@Body request: AuthLoginRequest
	): Response<AuthLoginResponse>
}