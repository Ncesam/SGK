package com.ncesam.sgk2026.data.remote

import com.ncesam.sgk2026.data.remote.dto.BookingBody
import com.ncesam.sgk2026.data.remote.dto.BookingDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface BookingApi {
	@POST("/api/collections/booking/records")
	suspend fun addBooking(
		@Header("Authorization") token: String,
		@Body booking: BookingBody
	): Response<BookingDto>

	@GET("/api/collections/booking/records")
	suspend fun getAllBooking(
		@Header("Authrozation") token: String,
		@Query("filter") filter: String
	): Response<List<BookingDto>>
}