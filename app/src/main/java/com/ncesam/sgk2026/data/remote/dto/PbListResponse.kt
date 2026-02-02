package com.ncesam.sgk2026.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PbListResponse<T>(
	val page: Int,
	@SerialName("perPage") val perPage: Int,
	@SerialName("totalItems") val totalItems: Int,
	val items: List<T>
)