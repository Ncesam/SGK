package com.ncesam.sgk2026.domain.viewModels

import androidx.lifecycle.ViewModel


sealed interface LoginEvent {
	data class EmailChanged(val email: String) : LoginEvent
	data class PasswordChanged(val password: String) : LoginEvent
	object LoginClicked : LoginEvent
	object OAuthClicked : LoginEvent
}


class LoginViewModel(
	private val loginUseCase: LoginUseCase
) : ViewModel() {

}