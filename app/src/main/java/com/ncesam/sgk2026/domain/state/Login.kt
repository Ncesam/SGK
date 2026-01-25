package com.ncesam.sgk2026.domain.state

data class LoginState(
	val email: String = "",
	val password: String = "",
	val isLoading: Boolean = false,
	val emailError: String = "",
	val passwordError: String = "",
) {
	val isButtonEnabled: Boolean get() = email.isNotBlank() && password.isNotBlank()
}

