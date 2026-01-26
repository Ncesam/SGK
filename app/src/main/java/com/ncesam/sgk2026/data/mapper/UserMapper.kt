package com.ncesam.sgk2026.data.mapper

import com.ncesam.sgk2026.data.remote.dto.UserDto
import com.ncesam.sgk2026.data.utils.PocketBaseFormatter
import com.ncesam.sgk2026.domain.models.Gender
import com.ncesam.sgk2026.domain.models.User
import java.time.LocalDate

fun UserDto.toDomain(): User {
	val born = LocalDate.parse(this.born, PocketBaseFormatter)
	val gender = if (this.gender == "female") Gender.Female else Gender.Male
	val avatar = if (this.avatar.isNullOrBlank()) "add" else this.avatar
	return User(
		this.id,
		this.email,
		this.firstName,
		this.lastName,
		this.fatherName,
		born,
		gender,
		avatar
	)
}