package com.ncesam.sgk2026.domain.repositories

import com.ncesam.sgk2026.domain.models.Booking
import com.ncesam.sgk2026.domain.models.CartItem


interface ShopCartRepository {
	suspend fun addBooking(vararg bookings: Booking)

	suspend fun addBooking(bookings: List<Booking>)
	
	suspend  fun updateQuantity(id: Int, quantity: Int)
	suspend fun deleteBooking(id: Int)
	suspend fun deleteAllBookings()
	suspend fun getAllBooking(): Result<List<CartItem>>
	suspend fun orderAllBooking()
}