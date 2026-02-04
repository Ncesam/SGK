package com.ncesam.sgk2026.domain.states

import java.time.LocalDate

data class BookingState(
	val hotelId: String = "",
	val userId: String = "",
	val dateFrom: LocalDate? = null,
	val dateTo: LocalDate? = null,
	val number: String = "",
	val nameBooked: String = "",
)


sealed interface BookingEvent {
	data class DateFromChanged(val value: String) : BookingEvent
	data class DateToChanged(val value: String) : BookingEvent
	data class NumberChanged(val value: String) : BookingEvent
	data class NameBookedChanged(val value: String) : BookingEvent
	object AddToCart: BookingEvent
}

sealed interface BookingEffect {
	object GoToCart: BookingEffect
}