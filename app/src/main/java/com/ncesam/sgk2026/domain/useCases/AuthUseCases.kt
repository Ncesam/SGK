package com.ncesam.sgk2026.domain.useCases

import com.ncesam.sgk2026.data.utils.validatePassword
import com.ncesam.sgk2026.domain.models.User
import com.ncesam.sgk2026.domain.repositories.AppSettingsRepository
import com.ncesam.sgk2026.domain.repositories.AuthRepository
import com.ncesam.sgk2026.domain.states.RegistrationState
import kotlinx.coroutines.flow.first


class LoginUseCase(
	private val authRepository: AuthRepository,
	private val appSettingsRepository: AppSettingsRepository
) {
	suspend operator fun invoke(email: String, password: String): Result<User> {
		return authRepository.login(email, password).map { (token, user) ->
			appSettingsRepository.saveToken(token)
			user
		}
	}
}

class RefreshTokenUseCase(
	private val authRepository: AuthRepository,
	private val appSettingsRepository: AppSettingsRepository
) {
	suspend operator fun invoke(): Result<String> {
		val token = appSettingsRepository.tokenFlow.first()

		if (token.isNullOrBlank()) {
			return Result.failure(Exception("User is not authorized"))
		}

		return authRepository.refreshToken(token)
			.map { value ->
				appSettingsRepository.saveToken(value.first)
				appSettingsRepository.saveUserId(value.second.id)
				value.first
			}
	}
}

class RegisterUseCase(
	private val authRepository: AuthRepository,
) {
	suspend operator fun invoke(state: RegistrationState): Result<User> {
		val params = state.toParams()
		return if (params != null && validatePassword(params.password).isValid()) {
			authRepository.register(params).map { user ->
				user
			}
		} else {
			Result.failure(Exception("Form is not filled"))
		}
	}
}

