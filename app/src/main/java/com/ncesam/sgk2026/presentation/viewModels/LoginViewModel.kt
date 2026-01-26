package com.ncesam.sgk2026.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ncesam.sgk2026.domain.state.LoginState
import com.ncesam.sgk2026.domain.useCases.LoginUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


sealed interface LoginEvent {
	data class EmailChanged(val email: String) : LoginEvent
	data class PasswordChanged(val password: String) : LoginEvent
	object LoginClicked : LoginEvent
	object OAuthClicked : LoginEvent
}


class LoginViewModel(
	private val loginUseCase: LoginUseCase
) : ViewModel() {
	private val _state = MutableStateFlow(LoginState())
	val state = _state.asStateFlow()

	private val _effect = Channel<LoginEffect>

	suspend fun onEvent(event: LoginEvent) {
		when (event) {
			is LoginEvent.EmailChanged -> {
				_state.update { it.copy(email = event.email, emailError = null)}
			}
			is LoginEvent.PasswordChanged -> {
				_state.update { it.copy(password = event.password, passwordError = null)}
			}
			is LoginEvent.LoginClicked -> performLogin()
		}
	}
	private suspend fun performLogin() {
		_state.update { it.copy(isLoading = true) }
		loginUseCase(_state.value.email, _state.value.password)
		_state.update {it.copy()}
	}
}