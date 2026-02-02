package com.ncesam.sgk2026.data.mapper

import com.ncesam.sgk2026.data.remote.dto.BookingDto
import com.ncesam.sgk2026.data.utils.PocketBaseFormatter
import com.ncesam.sgk2026.domain.models.Booking
import java.time.LocalDate


fun BookingDto.toDomain(): Booking {
	val dateFromDate = LocalDate.parse(dateFrom, PocketBaseFormatter)
	val dateToDate = LocalDate.parse(dateTo, PocketBaseFormatter)
	return Booking(
		id = id,
		hotelId = hotel,
		userId = user,
		dateFrom = dateFromDate,
		dateTo = dateToDate,
		phone = phone,
		nameBooker = nameBooked
	)
}