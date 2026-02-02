package com.ncesam.sgk2026.data.repositories

import com.ncesam.sgk2026.data.mapper.toDomain
import com.ncesam.sgk2026.data.remote.HotelApi
import com.ncesam.sgk2026.data.remote.utils.safeApiCall
import com.ncesam.sgk2026.domain.models.Hotel
import com.ncesam.sgk2026.domain.repositories.HotelRepository
import com.ncesam.sgk2026.domain.repositories.TokenManager

class HotelRepositoryImpl(private val hotelApi: HotelApi, private val tokenManager: TokenManager) :
	HotelRepository {
	override suspend fun getAllHotels(): Result<List<Hotel>> {
		val token = tokenManager.getValidToken()
		if (token.isNullOrBlank()) {
			return Result.failure(IllegalArgumentException("Token are empty"))
		}
		return safeApiCall(
			call = {
				hotelApi.getAllHotel(token)
			},
			mapper = { body -> body.items.map { hotelDto -> hotelDto.toDomain() } }
		)
	}

}