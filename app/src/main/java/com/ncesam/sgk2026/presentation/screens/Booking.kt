package com.ncesam.sgk2026.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncesam.sgk2026.domain.navigation.AppRoute
import com.ncesam.sgk2026.domain.states.BookingEffect
import com.ncesam.sgk2026.domain.states.BookingEvent
import com.ncesam.sgk2026.domain.states.BookingState
import com.ncesam.sgk2026.presentation.navigation.AppNavigator
import com.ncesam.sgk2026.presentation.viewModels.BookingViewModel
import com.ncesam.uikit.components.AppButton
import com.ncesam.uikit.components.AppButtonStyle
import com.ncesam.uikit.components.AppInput
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider
import com.ncesam.uikit.foundation.ScreenProvider
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookingContent(state: BookingState, onEvent: (BookingEvent) -> Unit) {
	var dateFromFocused by remember { mutableStateOf(false) }
	var dateToFocused by remember { mutableStateOf(false) }
	var numberFocused by remember { mutableStateOf(false) }
	var nameBookedFocused by remember { mutableStateOf(false) }


	val colors = AppTheme.colors
	val typography = AppTheme.typography

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(colors.white)
			.padding(20.dp)
			.statusBarsPadding()
			.imePadding(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.SpaceBetween
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			BasicText(text = "Бронирование", style = typography.h2SemiBold)
			AppInput(
				{ state -> dateFromFocused = state.isFocused },
				{ text -> onEvent(BookingEvent.DateFromChanged(text)) },
				{},
				placeholder = "--.--.----",
				helperText = "Дата заезда", errorText = state.errorDateFrom,
				value = state.dateFrom,
				isFocused = dateFromFocused
			)
			AppInput(
				{ state -> dateToFocused = state.isFocused },
				{ text -> onEvent(BookingEvent.DateToChanged(text)) },
				{},
				placeholder = "--.--.----",
				helperText = "Дата выезда",
				errorText = state.errorDateTo,
				value = state.dateTo,
				isFocused = dateToFocused
			)
			AppInput(
				{ state -> numberFocused = state.isFocused },
				{ text -> onEvent(BookingEvent.NumberChanged(text)) },
				{},
				placeholder = "+7----------",
				helperText = "Номер телефона",
				errorText = state.errorNumber,
				value = state.number,
				isFocused = numberFocused
			)
			AppInput(
				{ state -> nameBookedFocused = state.isFocused },
				{ text -> onEvent(BookingEvent.NameBookedChanged(text)) },
				{},
				placeholder = "Введите имя",
				helperText = "Имя",
				value = state.nameBooked,
				isFocused = nameBookedFocused
			)

		}
		AppButton(
			style = AppButtonStyle.Accent,
			content = "Подтвердить",
			onClick = { onEvent(BookingEvent.AddToCart) }
		)
	}
}

@Composable
fun BookingScreen(viewModel: BookingViewModel = koinViewModel()) {
	val scope = rememberCoroutineScope()
	val state by viewModel.state.collectAsState()
	val bottomTabs = AppNavigator.bottomTabs
	val navigator = AppNavigator.navigator

	bottomTabs.show()
	LaunchedEffect(Unit) {
		viewModel.effect.collect { effect ->
			when (effect) {
				BookingEffect.GoToCart -> {navigator.navigate(AppRoute.ShopCart)}
			}
		}
	}

	BookingContent(state) { event -> scope.launch { viewModel.onEvent(event) }}



}

@Preview
@Composable
fun PreviewBookingContent() {
	val state = BookingState()
	AppThemeProvider {
		ScreenProvider {
			BookingContent(state) { }
		}
	}
}