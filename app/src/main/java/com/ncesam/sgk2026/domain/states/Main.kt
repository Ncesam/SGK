package com.ncesam.sgk2026.domain.states

import com.ncesam.sgk2026.domain.models.Hotel


data class MainState(
	val categories: List<String> = emptyList(),
	val salesHotels: List<Hotel> = emptyList(),
	val allHotels: List<Hotel> = emptyList(),
	val filteredHotels: List<Hotel> = emptyList(),
	val selectedCategory: String? = null,
	val isLoading: Boolean = false,
	val search: String = "",
	val selectedHotel: Hotel? = null
)

sealed interface MainEvent {
	data class SearchChanged(val value: String) : MainEvent
	data class SelectedCategoryChanged(val value: String) : MainEvent
	data class AddToCart(val id: String) : MainEvent
	data class ViewHotel(val id: String) : MainEvent
	data class GoToSearch(val value: String) : MainEvent
	object Profile : MainEvent
}

sealed interface MainEffect {
	data class ShowSnackBar(val msg: String) : MainEffect
	data class GoToBooking(val hotelId: String) : MainEffect
	data class GoToSearch(val value: String) : MainEffect
	object GoToProfile : MainEffect

}