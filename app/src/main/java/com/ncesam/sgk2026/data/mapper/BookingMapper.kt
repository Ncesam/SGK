package com.ncesam.sgk2026.data.mapper

import com.ncesam.sgk2026.data.remote.dto.BookingDto
import com.ncesam.sgk2026.data.utils.PocketBaseFormatter
import com.ncesam.sgk2026.domain.models.Booking
import java.time.LocalDate


fun BookingDto.toDomain(): Booking {
	return Booking(
		id = id,
		hotelId = hotel,
		dateFrom = dateFrom,
		dateTo = dateTo,
		phone = phone,
		nameBooker = nameBooked
	)
}

