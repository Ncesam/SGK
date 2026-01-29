package com.ncesam.sgk2026.domain.states

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class RegistrationState(
	val firstName: String = "",
	val lastName: String = "",
	val fatherName: String = "",
	val born: String = "",
	val gender: String = "",
	val email: String = "",
	val password: String = "",
	val retryPassword: String = "",
	val rulesPassword: PasswordRules = PasswordRules(),
	val emailError: String? = null,
	val passwordError: String? = null,
	val genderError: String? = null,
	val bornError: String? = null,
) {
	val isPasswordButtonEnabled: Boolean
		get() = listOf(
			firstName,
			lastName,
			fatherName,
			born,
			gender,
			email
		).all { it.isNotBlank() }
	
	val isPasswordRetryEqual: Boolean
		get() = password == retryPassword

	fun toParams(): RegistrationParams? {
		return RegistrationParams(
			firstName = firstName.ifBlank { return null },
			lastName = lastName.ifBlank { return null },
			fatherName = fatherName.ifBlank {return null},
			born = born.ifBlank {return null},
			gender = gender.ifBlank {return null},
			email = email.ifBlank {return null},
			password = password.ifBlank {return null},
		)
	}
}


@Serializable
data class RegistrationParams(
	@SerialName("first_name") val firstName: String,
	@SerialName("last_name") val lastName: String,
	@SerialName("father_name") val fatherName: String,
	val born: String,
	val gender: String,
	val email: String,
	val password: String
)

sealed interface RegistrationEvent {
	data class FirstNameChanged(val value: String) : RegistrationEvent
	data class LastNameChanged(val value: String) : RegistrationEvent
	data class FatherNameChanged(val value: String) : RegistrationEvent
	data class BornChanged(val value: String) : RegistrationEvent
	data class GenderChanged(val value: String) : RegistrationEvent
	data class EmailChanged(val value: String) : RegistrationEvent
	data class PasswordChanged(val value: String) : RegistrationEvent
	data class RetryPasswordChanged(val value: String): RegistrationEvent
	data class PinCodeChanged(val value: Int) : RegistrationEvent
	object SaveUser : RegistrationEvent
	object GoToPassword : RegistrationEvent
	object GoToPinCode : RegistrationEvent
}

sealed interface RegistrationEffect {
	data class ShowSnackBar(val msg: String) : RegistrationEffect
	object NavigateToCreatePasswordScreen : RegistrationEffect
	object NavigateToCreatePinCodeScreen : RegistrationEffect
	object NavigateToLogin : RegistrationEffect
}

data class PasswordRules(
	val minLength: Boolean = false,
	val hasUpper: Boolean = false,
	val hasLower: Boolean = false,
	val hasDigit: Boolean = false,
	val hasSpecial: Boolean = false
) {
	fun isValid(): Boolean {
		return listOf(
			hasDigit,
			hasUpper,
			hasLower,
			minLength,
			hasSpecial
		).any { it }
	}
}