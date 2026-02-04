package com.ncesam.sgk2026.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ncesam.uikit.foundation.AppThemeProvider
import com.ncesam.uikit.foundation.ScreenProvider

@Composable
fun BookingContent(state: BookingState, onEvent: (BookingEvent) -> Unit){

}

@Composable
fun BookingScreen(viewModel: BookingViewModel = koinViewModel()) {

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