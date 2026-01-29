package com.ncesam.sgk2026.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncesam.sgk2026.domain.navigation.AppRoute
import com.ncesam.sgk2026.domain.states.SplashState
import com.ncesam.sgk2026.domain.useCases.RefreshTokenUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SplashViewModel(private val refreshTokenUseCase: RefreshTokenUseCase) : ViewModel() {
	private val _state = MutableStateFlow<SplashState>(SplashState())
	val state = _state.asStateFlow()
	private val _navigationEvent = MutableSharedFlow<AppRoute>(
		replay = 0,
		extraBufferCapacity = 1
	)
	val navigationEvent = _navigationEvent.asSharedFlow()

	init {
		viewModelScope.launch {
			delay(2000)
			refreshTokenUseCase().onSuccess {
				_state.update { state ->
					state.copy(
						isLoading = false,
					)
				}
				_navigationEvent.emit(AppRoute.MainGraph)
			}
				.onFailure {
					_state.update { state ->
						state.copy(
							isLoading = false,
						)
					}
					_navigationEvent.emit(AppRoute.AuthGraph)
				}
		}
	}

}

