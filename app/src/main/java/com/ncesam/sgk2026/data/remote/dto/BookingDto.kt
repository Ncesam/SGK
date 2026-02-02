package com.ncesam.sgk2026.data.remote.dto

import kotlinx.serialization.SerialName

data class BookingDto(
	val id: String,
	val user: String,
	val dateFrom: String,
	val dateTo: String,
	val hotel: String,
	@SerialName("name_booked") val nameBooked: String,
	val phone: String,
)