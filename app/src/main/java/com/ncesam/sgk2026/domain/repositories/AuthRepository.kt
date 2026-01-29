package com.ncesam.sgk2026.domain.repositories

import com.ncesam.sgk2026.domain.models.User
import com.ncesam.sgk2026.domain.states.RegistrationParams
import com.ncesam.sgk2026.domain.states.RegistrationState

interface AuthRepository {
	suspend fun login(email: String, password: String): Result<Pair<String, User>>
	suspend fun refreshToken(token: String): Result<Pair<String, User>>
	suspend fun register(state: RegistrationParams): Result<User>
}

