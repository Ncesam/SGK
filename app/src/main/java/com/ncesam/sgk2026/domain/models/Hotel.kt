package com.ncesam.sgk2026.domain.models

data class Hotel(
	val id: String,
	val title: String,
	val description: String,
	val image: String,
	val cost: String,
	val facilities: List<Facility>
)
