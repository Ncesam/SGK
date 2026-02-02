package com.ncesam.sgk2026.data.repositories

import android.util.Log
import com.ncesam.sgk2026.data.mapper.toDomain
import com.ncesam.sgk2026.data.remote.AuthApi
import com.ncesam.sgk2026.data.remote.dto.AuthLoginRequest
import com.ncesam.sgk2026.domain.models.User
import com.ncesam.sgk2026.domain.repositories.AuthRepository
import com.ncesam.sgk2026.domain.states.RegistrationParams

class AuthRepositoryImpl(private val api: AuthApi) : AuthRepository {
	override suspend fun login(email: String, password: String): Result<Pair<String, User>> {
		try {
			val request = AuthLoginRequest(email, password)
			val response = api.login(request)
			if (response.isSuccessful) {
				val dto = response.body()
				if (dto != null) {
					val user = dto.record.toDomain()
					return Result.success(Pair(dto.token, user))
				} else {
					return Result.failure(Exception("Body is empty"))
				}
			} else {
				return Result.failure(Exception("Server error ${response.code()}"))
			}
		} catch (e: Exception) {
			return Result.failure(e)
		}
	}

	override suspend fun refreshToken(token: String): Result<Pair<String, User>> {
		try {
			val headerToken = "Bearer $token"
			val response = api.refreshToken(headerToken)
			if (response.isSuccessful) {
				val dto = response.body()
				if (dto != null) {
					val user = dto.record.toDomain()
					return Result.success(Pair(dto.token, user))
				} else {
					return Result.failure(Exception("Body is empty"))
				}
			} else {
				return Result.failure(Exception("Server error ${response.code()}"))
			}

		} catch (e: Exception) {
			return Result.failure(e)
		}
	}

	override suspend fun register(state: RegistrationParams): Result<User> {
		try {
			val response = api.register(state)
			if (response.isSuccessful) {
				val dto = response.body()
				if (dto != null) {
					val user = dto.toDomain()
					return Result.success(user)
				} else {
					return Result.failure(Exception("Body is empty"))
				}
			} else {
				return Result.failure(Exception("Server error ${response.code()}"))
			}
		} catch (e: Exception) {
			return Result.failure(e)
		}
	}
}