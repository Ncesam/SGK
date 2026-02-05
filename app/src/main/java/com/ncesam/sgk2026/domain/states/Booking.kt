package com.ncesam.sgk2026.domain.states

data class BookingState(
	val hotelId: String = "",
	val dateFrom: String = "",
	val dateTo: String = "",
	val number: String = "",
	val nameBooked: String = "",
	val errorDateFrom: String? = null,
	val errorDateTo: String? = null,
	val errorNumber: String? = null,
)


sealed interface BookingEvent {
	data class DateFromChanged(val value: String) : BookingEvent
	data class DateToChanged(val value: String) : BookingEvent
	data class NumberChanged(val value: String) : BookingEvent
	data class NameBookedChanged(val value: String) : BookingEvent
	object AddToCart : BookingEvent
}

sealed interface BookingEffect {
	object GoToCart : BookingEffect
}