package com.ncesam.sgk2026.domain.models

import java.time.LocalDate


data class Booking(
	val id: String,
	val hotelId: String,
	val userId: String,
	val dateFrom: LocalDate,
	val dateTo: LocalDate,
	val phone: String,
	val nameBooker: String,
)