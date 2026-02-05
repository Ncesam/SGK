package com.ncesam.sgk2026.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopCart")
data class CartItemEntity(
	@PrimaryKey(true) val id: Int? = null,
	val dateTo: String,
	val dateFrom: String,
	val nameBooked: String,
	val phone: String,
	val hotelId: String,
	val quantity: Int
)