package com.ncesam.sgk2026.domain.useCases

import android.Manifest
import android.hardware.biometrics.BiometricManager
import androidx.annotation.RequiresPermission
import com.ncesam.sgk2026.data.utils.validatePassword
import com.ncesam.sgk2026.domain.models.User
import com.ncesam.sgk2026.domain.repositories.AuthRepository
import com.ncesam.sgk2026.domain.repositories.TokenManager
import com.ncesam.sgk2026.domain.repositories.UserSettingsRepository
import com.ncesam.sgk2026.domain.states.RegistrationState


class LoginUseCase(
	private val authRepository: AuthRepository,
	private val tokenManager: TokenManager
) {
	suspend operator fun invoke(email: String, password: String): Result<User> {
		return authRepository.login(email, password).map { (token, user) ->
			tokenManager.saveToken(token)
			user
		}
	}
}

class RefreshTokenUseCase(
	private val tokenManager: TokenManager
) {
	suspend operator fun invoke(): Result<String> {
		val token = tokenManager.getValidToken()
		if (token.isNullOrBlank()) {
			return Result.failure(Exception("Token are empty"))
		}
		return Result.success(token)
	}
}

class RegisterUseCase(
	private val authRepository: AuthRepository,
	private val appSettingsRepository: UserSettingsRepository
) {
	suspend operator fun invoke(state: RegistrationState): Result<User> {
		val params = state.toParams()
		if (state.useBiometric != null) {
			appSettingsRepository.saveUseBiometric(state.useBiometric)
		}
		return if (params != null && validatePassword(params.password).isValid()) {
			authRepository.register(params).map { user ->
				appSettingsRepository.savePinCode(state.pinCode)
				user
			}
		} else {
			Result.failure(Exception("Form is not filled"))
		}
	}
}


class CanUseBiometric(private val biometricManager: BiometricManager) {
	@RequiresPermission(Manifest.permission.USE_BIOMETRIC)
	operator fun invoke(): Boolean {
		return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
	}
}

