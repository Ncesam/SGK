package com.ncesam.sgk2026.domain.models

import java.util.Date


data class User(
	val id: String,
	val email: String,
	val firstName: String,
	val lastName: String,
	val fatherName: String,
	val born: Date,
	val gender: Gender,
	val avatar: String,
)

enum class Gender(val value: String) {
	Male("male"),
	Female("female")
}