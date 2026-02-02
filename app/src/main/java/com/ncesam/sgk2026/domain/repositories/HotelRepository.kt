package com.ncesam.sgk2026.domain.repositories

import com.ncesam.sgk2026.domain.models.Hotel


interface HotelRepository {
	suspend fun getAllHotels(): Result<List<Hotel>>
}