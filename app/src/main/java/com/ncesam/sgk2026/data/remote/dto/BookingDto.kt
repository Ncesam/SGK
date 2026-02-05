package com.ncesam.sgk2026.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BookingDto(
	val id: String,
	val user: String,
	val dateFrom: String,
	val dateTo: String,
	val hotel: String,
	@SerialName("name_booked") val nameBooked: String,
	val phone: String,
)

@Serializable
data class BookingBody(
	val userId: String,
	val hotelId: String,
	val dateFrom: String,
	val dateTo: String,
	val phone: String,
	val nameBooker: String
)