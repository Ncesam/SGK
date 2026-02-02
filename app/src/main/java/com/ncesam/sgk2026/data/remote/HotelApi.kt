package com.ncesam.sgk2026.data.remote

import com.ncesam.sgk2026.data.remote.dto.HotelDto
import com.ncesam.sgk2026.data.remote.dto.PbListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface HotelApi {
	@GET("/api/collections/hotels/records")
	suspend fun getAllHotel(
		@Header("Authorization") token: String
	): Response<PbListResponse<HotelDto>>
}