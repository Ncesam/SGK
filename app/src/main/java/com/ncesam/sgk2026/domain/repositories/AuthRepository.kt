package com.ncesam.sgk2026.domain.repositories

import com.ncesam.sgk2026.domain.models.Gender
import com.ncesam.sgk2026.domain.models.User
import java.util.Date

interface AuthRepository {
	suspend fun login(email: String, password: String): Result<Pair<String, User>>
}