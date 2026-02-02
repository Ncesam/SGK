package com.ncesam.sgk2026.data.mapper

import com.ncesam.sgk2026.data.remote.dto.HotelDto
import com.ncesam.sgk2026.domain.models.Facility
import com.ncesam.sgk2026.domain.models.Hotel


fun HotelDto.toDomain(): Hotel {
	val enumFacility = facilities.mapNotNull { value ->
		try {
			Facility.valueOf(value)
		} catch (e: IllegalArgumentException) {
			null
		}
	}
	return Hotel(
		id = id,
		title = title,
		description = description,
		image = image,
		cost = cost.toString(),
		facilities = enumFacility
	)
}