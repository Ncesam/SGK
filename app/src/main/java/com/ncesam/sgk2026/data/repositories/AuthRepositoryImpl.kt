package com.ncesam.sgk2026.data.repositories

import com.ncesam.sgk2026.data.mapper.toDomain
import com.ncesam.sgk2026.data.remote.AuthApi
import com.ncesam.sgk2026.data.remote.dto.AuthLoginRequest
import com.ncesam.sgk2026.domain.models.User
import com.ncesam.sgk2026.domain.repositories.AuthRepository

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
			}else {
				return Result.failure(Exception("Server error ${response.code()}"))
			}
		} catch (e: Exception) {
			Result.failure<Exception>(e)
		}
	}
}