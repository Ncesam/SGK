package com.ncesam.sgk2026.domain.states

data class LoginState(
	val email: String = "",
	val password: String = "",
	val isLoading: Boolean = false,
	val emailError: String? = null,
	val passwordError: String? = null,
) {
	val isButtonEnabled: Boolean get() = email.isNotBlank() && password.isNotBlank()
}

sealed interface LoginEffect {
	data class ShowSnackBar(val msg: String) : LoginEffect
	object NavigateToHome : LoginEffect
	object NavigateToRegister : LoginEffect
}


sealed interface LoginEvent {
	data class EmailChanged(val email: String) : LoginEvent
	data class PasswordChanged(val password: String) : LoginEvent
	object LoginClicked : LoginEvent
	object OAuthClicked : LoginEvent
	object RegisterClicked : LoginEvent
}
