package com.ncesam.sgk2026.domain.states

import com.ncesam.sgk2026.domain.models.CartItem


data class ShopCartState(
	val totalCost: Int = 0,
	val items: List<CartItem> = emptyList()
)

sealed interface ShopCartEvent {
	data class Increment(val id: Int): ShopCartEvent
	data class Decrement(val id: Int): ShopCartEvent
	data class Delete(val id: Int): ShopCartEvent
	object Buy: ShopCartEvent
}
