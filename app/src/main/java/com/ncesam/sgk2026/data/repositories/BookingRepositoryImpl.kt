package com.ncesam.sgk2026.data.repositories

import com.ncesam.sgk2026.data.mapper.toDomain
import com.ncesam.sgk2026.data.remote.BookingApi
import com.ncesam.sgk2026.data.remote.utils.safeApiCall
import com.ncesam.sgk2026.domain.models.Booking
import com.ncesam.sgk2026.domain.models.BookingForm
import com.ncesam.sgk2026.domain.repositories.BookingRepository
import com.ncesam.sgk2026.domain.repositories.TokenManager
import com.ncesam.sgk2026.domain.repositories.UserSettingsRepository
import kotlinx.coroutines.flow.first


class BookingRepositoryImpl(
	private val bookingApi: BookingApi,
	private val tokenManager: TokenManager,
	private val userSettingsRepository: UserSettingsRepository
) : BookingRepository {
	override suspend fun bookHotel(form: BookingForm): Result<Booking> {
		val token = tokenManager.getValidToken()
		if (token.isNullOrBlank()) {
			return Result.failure(IllegalArgumentException("Token are empty"))
		}
		return safeApiCall(
			call = {
				bookingApi.addBooking(token, form)
			},
			mapper = { body -> body.toDomain() }
		)
	}

	override suspend fun getAllBooking(): Result<List<Booking>> {
		val userId = userSettingsRepository.userIdFlow.first()
		if (userId.isNullOrBlank()) {
			return Result.failure(Exception("User are empty"))
		}
		val token = tokenManager.getValidToken()
		if (token.isNullOrBlank()) {
			return Result.failure(Exception("Token are empty"))
		}
		return safeApiCall(
			call = {
				val filter = "(user=$userId)"
				bookingApi.getAllBooking(token, filter)
			},
			mapper = { body -> body.map { dto -> dto.toDomain() } }
		)
	}

}