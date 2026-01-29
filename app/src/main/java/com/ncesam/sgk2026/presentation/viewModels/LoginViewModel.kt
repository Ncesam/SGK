package com.ncesam.sgk2026.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.ncesam.sgk2026.domain.states.LoginEffect
import com.ncesam.sgk2026.domain.states.LoginEvent
import com.ncesam.sgk2026.domain.states.LoginState
import com.ncesam.sgk2026.domain.useCases.LoginUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update


class LoginViewModel(
	private val loginUseCase: LoginUseCase
) : ViewModel() {
	private val _state = MutableStateFlow(LoginState())
	val state = _state.asStateFlow()

	private val _effect = Channel<LoginEffect>()
	val effect = _effect.receiveAsFlow()

	suspend fun onEvent(event: LoginEvent) {
		when (event) {
			is LoginEvent.EmailChanged -> {
				_state.update { it.copy(email = event.email, emailError = null) }
			}

			is LoginEvent.PasswordChanged -> {
				_state.update { it.copy(password = event.password, passwordError = null) }
			}

			is LoginEvent.LoginClicked -> performLogin()

			is LoginEvent.OAuthClicked -> {
				_effect.send(LoginEffect.ShowSnackBar("Не реализовано! :("))
			}

			is LoginEvent.RegisterClicked -> {
				_effect.send(LoginEffect.NavigateToRegister)
			}
		}
	}

	private suspend fun performLogin() {
		_state.update { it.copy(isLoading = true) }
		loginUseCase(_state.value.email, _state.value.password).onSuccess {
			_state.update { it.copy() }
			_effect.send(LoginEffect.ShowSnackBar("Вы успешно вошли"))
			delay(2000)
			_effect.send(LoginEffect.NavigateToHome)
		}.onFailure {
			_state.update {
				it.copy(
					emailError = "Неправильный E-Mail",
					passwordError = "Неправильный пароль"
				)
			}
		}
	}
}