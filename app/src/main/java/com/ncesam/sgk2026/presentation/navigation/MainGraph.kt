package com.ncesam.sgk2026.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ncesam.sgk2026.domain.navigation.AppRoute
import com.ncesam.sgk2026.presentation.screens.BookingScreen
import com.ncesam.sgk2026.presentation.screens.MainScreen


fun NavGraphBuilder.mainGraph() {
	navigation<AppRoute.MainGraph>(
		startDestination = AppRoute.Main
	) {
		composable<AppRoute.Main> {
			MainScreen()
		}
		composable<AppRoute.Booking> {
			BookingScreen()
		}
		composable<AppRoute.ShopCart> {

		}
		composable<AppRoute.Profile> {

		}
	}
}