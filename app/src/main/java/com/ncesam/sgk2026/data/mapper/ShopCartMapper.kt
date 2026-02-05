package com.ncesam.sgk2026.data.mapper

import com.ncesam.sgk2026.domain.entity.CartItemEntity
import com.ncesam.sgk2026.domain.models.CartItem


fun CartItemEntity.toDomain(): CartItem {
	return CartItem(
		quantity = quantity,
		dateFrom = dateFrom,
		dateTo = dateTo,
		nameBooked = nameBooked,
		phone = phone,
		hotelId = hotelId
	)
}