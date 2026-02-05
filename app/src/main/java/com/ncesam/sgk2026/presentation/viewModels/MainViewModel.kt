package com.ncesam.sgk2026.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncesam.sgk2026.domain.states.MainEffect
import com.ncesam.sgk2026.domain.states.MainEvent
import com.ncesam.sgk2026.domain.states.MainState
import com.ncesam.sgk2026.domain.useCases.GetAllHotelUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainViewModel(private val getAllHotelsUseCase: GetAllHotelUseCase) : ViewModel() {
	private val _state =
		MutableStateFlow(MainState(categories = listOf("Популярное", "Все включено", "Близкие")))
	val state = _state.asStateFlow()

	private val _effect = Channel<MainEffect>()
	val effect = _effect.receiveAsFlow()

	init {
		viewModelScope.launch {
			getAllHotelsUseCase().onSuccess { hotels ->
				_state.update { state ->
					state.copy(
						allHotels = hotels,
					)
				}
			}.onFailure { exception ->
				_effect.send(MainEffect.ShowSnackBar(exception.message!!))
			}
		}
	}

	suspend fun onEvent(event: MainEvent) {
		when (event) {
			is MainEvent.AddToCart -> {
				_effect.send(MainEffect.GoToBooking(event.id))
			}
			is MainEvent.ViewHotel -> {
				_state.update { state -> state.copy(selectedHotel = state.allHotels.find { hotel -> hotel.id == event.id }) }
			}

			is MainEvent.Profile -> {
				_effect.send(MainEffect.GoToProfile)
			}

			is MainEvent.GoToSearch -> {
				_effect.send(MainEffect.GoToSearch(event.value))
			}

			is MainEvent.SearchChanged -> {
				_state.update { state -> state.copy(search = event.value) }
			}

			is MainEvent.SelectedCategoryChanged -> {
				_state.update { state -> state.copy(selectedCategory = event.value) }
			}
		}
	}

}