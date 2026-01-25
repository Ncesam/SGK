package com.ncesam.sgk2026.domain.useCases

import com.ncesam.sgk2026.domain.models.User
import com.ncesam.sgk2026.domain.repositories.AuthRepository


class LoginUseCases(
	private val repository: AuthRepository
) {
	suspend operator fun invoke(email: String, password: String): User {
		require(email.isNotBlank()) {"Email must be filled"}
		require(password.length < 8) { "Password length must be 8 or more" }
		require(password.any { it.isLowerCase() }) { "Password must contain lower case characters" }
		require(password.any { it.isUpperCase() }) { "Password must contain upper case characters" }
		require(password.any { it.isDigit() }) { "Password must contain digital" }
		require(password.any() { it in "!@#\\$" }) { "Password must contain special symbols" }

		val user = repository.login(email, password)
		return user
	}
}