package com.ncesam.sgk2026.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.ncesam.sgk2026.data.utils.validateDate
import com.ncesam.sgk2026.data.utils.validateEmail
import com.ncesam.sgk2026.data.utils.validatePassword
import com.ncesam.sgk2026.domain.states.RegistrationEffect
import com.ncesam.sgk2026.domain.states.RegistrationEvent
import com.ncesam.sgk2026.domain.states.RegistrationState
import com.ncesam.sgk2026.domain.useCases.RegisterUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class RegistrationViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
	private val _state = MutableStateFlow(RegistrationState())
	val state = _state.asStateFlow()

	private val _effect = Channel<RegistrationEffect>()
	val effect = _effect.receiveAsFlow()

	suspend fun onEvent(event: RegistrationEvent) {
		when (event) {
			is RegistrationEvent.FirstNameChanged -> {
				_state.update { current -> current.copy(firstName = event.value) }
			}

			is RegistrationEvent.LastNameChanged -> {
				_state.update { current -> current.copy(lastName = event.value) }
			}

			is RegistrationEvent.FatherNameChanged -> {
				_state.update { current -> current.copy(fatherName = event.value) }
			}

			is RegistrationEvent.EmailChanged -> {
				_state.update { current -> current.copy(email = event.value) }
			}

			is RegistrationEvent.BornChanged -> {
				_state.update { current -> current.copy(born = event.value) }
			}

			is RegistrationEvent.GenderChanged -> {
				_state.update { current -> current.copy(gender = event.value) }
			}

			is RegistrationEvent.PasswordChanged -> {
				val rules = validatePassword(event.value)
				_state.update { current ->
					current.copy(
						password = event.value,
						rulesPassword = rules
					)
				}
			}

			is RegistrationEvent.RetryPasswordChanged -> {
				_state.update { current ->
					val retryPassword = event.value
					current.copy(
						retryPassword = retryPassword,
						passwordError = if (retryPassword != current.password) "Пароли не совпадают" else null
					)
				}

			}

			is RegistrationEvent.PinCodeChanged -> {
				_state.update { current -> current.copy() }
			}


			is RegistrationEvent.SaveUser -> {
				registerUseCase(_state.value)
					.onSuccess {
						_effect.send(RegistrationEffect.NavigateToLogin)
					}
					.onFailure { _effect.send(RegistrationEffect.ShowSnackBar("Произошла ошибка во время регистрации")) }
			}

			is RegistrationEvent.GoToPassword -> {
				val current = state.value
				val bornError =
					if (validateDate(current.born)) "Введите дату рождения (2022-02-24)" else null
				val emailError = if (validateEmail(current.email)) "Введите E-Mail" else null
				val genderError =
					if (current.gender in listOf("male", "female")) null else "Укажите пол"
				if (current.isPasswordButtonEnabled) {
					if (listOf(bornError, emailError, genderError).any { !it.isNullOrBlank() }) {
						_state.update { current ->
							current.copy(
								bornError = bornError,
								emailError = emailError,
								genderError = genderError
							)
						}
					} else {
						_effect.send(RegistrationEffect.NavigateToCreatePasswordScreen)
					}
				}
			}

			is RegistrationEvent.GoToPinCode -> {
				if (state.value.rulesPassword.isValid()) {
					_effect.send(RegistrationEffect.NavigateToCreatePinCodeScreen)
				} else {
					_effect.send(RegistrationEffect.ShowSnackBar("Проверьте пароль"))
				}
			}
		}
	}

}