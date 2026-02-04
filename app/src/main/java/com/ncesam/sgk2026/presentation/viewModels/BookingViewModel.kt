package com.ncesam.sgk2026.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.ncesam.sgk2026.data.utils.validateDate
import com.ncesam.sgk2026.data.utils.validatePhone
import com.ncesam.sgk2026.domain.states.BookingEffect
import com.ncesam.sgk2026.domain.states.BookingEvent
import com.ncesam.sgk2026.domain.states.BookingState
import com.ncesam.sgk2026.domain.useCases.BookHotelUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BookingViewModel(private val bookHotelUseCase: BookHotelUseCase) : ViewModel() {
	private val _state = MutableStateFlow(BookingState())
	val state = _state.asStateFlow()


	private val _effect = Channel<BookingEffect>()
	val effect = _effect.receiveAsFlow()

	suspend fun onEvent(event: BookingEvent) {
		when (event) {
			is BookingEvent.DateFromChanged -> {

				if (validateDate(event.value)) {
					val date =  LocalDate.parse(event.value, DateTimeFormatter.ISO_DATE)
				_state.update { state -> state.copy(dateFrom = date) }
				} else {

				}
			}
			is BookingEvent.DateToChanged -> {
				if (validateDate(event.value)) {
					val date =  LocalDate.parse(event.value, DateTimeFormatter.ISO_DATE)
					_state.update { state -> state.copy(dateTo = date) }
				} else {

				}
			}
			is BookingEvent.NumberChanged -> {
				_state.update { state -> state.copy()}
				if (validatePhone(event.value)) {

				}
			}
		}
	}

}