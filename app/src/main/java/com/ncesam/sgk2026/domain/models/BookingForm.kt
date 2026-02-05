package com.ncesam.sgk2026.domain.models


data class BookingForm(
	val hotelId: String,
	val dateFrom: String,
	val dateTo: String,
	val phone: String,
	val nameBooker: String,
)