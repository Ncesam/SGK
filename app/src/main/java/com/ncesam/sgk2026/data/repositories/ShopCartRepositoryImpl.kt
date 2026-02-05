package com.ncesam.sgk2026.data.repositories

import com.ncesam.sgk2026.data.mapper.toDomain
import com.ncesam.sgk2026.data.room.ShopCartDao
import com.ncesam.sgk2026.domain.entity.CartItemEntity
import com.ncesam.sgk2026.domain.models.Booking
import com.ncesam.sgk2026.domain.models.BookingForm
import com.ncesam.sgk2026.domain.models.CartItem
import com.ncesam.sgk2026.domain.repositories.BookingRepository
import com.ncesam.sgk2026.domain.repositories.ShopCartRepository
import kotlinx.serialization.json.Json


val json = Json { ignoreUnknownKeys = true }

class ShopCartRepositoryImpl(
	private val shopCartDao: ShopCartDao,
	private val bookingRepository: BookingRepository
) : ShopCartRepository {

	override suspend fun addBooking(vararg bookings: Booking) {
		val entities = bookings.map { booking ->
			CartItemEntity(
				dateTo = booking.dateTo,
				dateFrom = booking.dateFrom,
				nameBooked = booking.nameBooker,
				phone = booking.phone,
				hotelId = booking.hotelId,
				quantity = 1
			)
		}
		shopCartDao.addItems(entities)
	}

	override suspend fun addBooking(bookings: List<Booking>) {
		val entities = bookings.map { booking ->
			CartItemEntity(
				dateTo = booking.dateTo,
				dateFrom = booking.dateFrom,
				nameBooked = booking.nameBooker,
				phone = booking.phone,
				hotelId = booking.hotelId,
				quantity = 1
			)
		}
		shopCartDao.addItems(entities)
	}

	override suspend fun updateQuantity(id: Int, quantity: Int) {
		shopCartDao.updateQuantity(id, quantity)
	}

	override suspend fun deleteBooking(id: Int) {
		shopCartDao.deleteItem(id)
	}

	override suspend fun deleteAllBookings() {
		shopCartDao.deleteAll()
	}

	override suspend fun getAllBooking(): Result<List<CartItem>> {
		try {
			val entities = shopCartDao.getAll()
			val domains = entities.map { entity ->
				entity.toDomain()
			}
			return Result.success(domains)
		} catch (e: Exception) {
			return Result.failure(e)
		}
	}

	override suspend fun orderAllBooking() {
		getAllBooking().onSuccess { bookings ->
			bookings.map { booking ->
				val quantity = booking.quantity
				for (i in 0..quantity) {
					bookingRepository.bookHotel(BookingForm(booking.hotelId, booking.dateFrom, booking.dateTo, booking.phone, booking.nameBooked))
				}
			}
		}

	}
}