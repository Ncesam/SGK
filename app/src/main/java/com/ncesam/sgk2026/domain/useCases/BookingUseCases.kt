package com.ncesam.sgk2026.domain.useCases

import com.ncesam.sgk2026.domain.models.Booking
import com.ncesam.sgk2026.domain.models.BookingForm
import com.ncesam.sgk2026.domain.repositories.BookingRepository


class BookHotelUseCase(private val bookingRepository: BookingRepository) {
	suspend operator fun invoke(form: BookingForm): Result<Booking> {
		return bookingRepository.bookHotel(form).map {
			booking -> booking
		}
	}
}