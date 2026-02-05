package com.ncesam.sgk2026.domain.models


data class CartItem(
	val quantity: Int,
	val dateFrom: String,
	val dateTo: String,
	val nameBooked: String,
	val phone: String,
	val hotelId: String,
)
