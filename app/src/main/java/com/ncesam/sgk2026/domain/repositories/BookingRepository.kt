package com.ncesam.sgk2026.domain.repositories

import com.ncesam.sgk2026.domain.models.Booking
import com.ncesam.sgk2026.domain.models.BookingForm

interface BookingRepository {
	suspend fun bookHotel(booking: BookingForm): Result<Booking>
	suspend fun getAllBooking(): Result<List<Booking>>
}