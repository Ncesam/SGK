package com.ncesam.sgk2026.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.ncesam.sgk2026.data.utils.validateDate
import com.ncesam.sgk2026.data.utils.validatePhone
import com.ncesam.sgk2026.domain.navigation.AppRoute
import com.ncesam.sgk2026.domain.states.BookingEffect
import com.ncesam.sgk2026.domain.states.BookingEvent
import com.ncesam.sgk2026.domain.states.BookingState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class BookingViewModel(
	private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
	private val _state = MutableStateFlow(BookingState())
	val state = _state.asStateFlow()

	init {
		val routeArgs = savedStateHandle.toRoute<AppRoute.Booking>()
		_state.update { state -> state.copy(hotelId = routeArgs.hotelId) }
	}


	private val _effect = Channel<BookingEffect>()
	val effect = _effect.receiveAsFlow()

	suspend fun onEvent(event: BookingEvent) {
		when (event) {
			is BookingEvent.DateFromChanged -> {
				_state.update { state -> state.copy(dateFrom = event.value) }
			}

			is BookingEvent.DateToChanged -> {
				_state.update { state -> state.copy(dateTo = event.value) }
			}

			is BookingEvent.NumberChanged -> {
				_state.update { state -> state.copy(number = event.value) }
			}

			is BookingEvent.NameBookedChanged -> {
				_state.update { state -> state.copy(nameBooked = event.value) }
			}

			BookingEvent.AddToCart -> {
				val errorDateFrom =
					if (!validateDate(_state.value.dateFrom)) "Введите дату (2022-02-24)" else null
				val errorDateTo =
					if (!validateDate(_state.value.dateTo)) "Введите дату (2022-02-24)" else null
				val errorNumber = if (!validatePhone(_state.value.number)) "+79876543210" else null
				if (listOf(errorDateFrom, errorDateTo, errorNumber).any { !it.isNullOrBlank() }) {
					_state.update { state ->
						state.copy(
							errorNumber = errorNumber,
							errorDateTo = errorDateTo,
							errorDateFrom = errorDateFrom,
							number = "",
							dateTo = "",
							dateFrom = ""
						)
					}
				}
			}
		}
	}
}
