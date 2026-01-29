package com.ncesam.sgk2026.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ncesam.sgk2026.domain.navigation.AppRoute


fun NavGraphBuilder.mainGraph() {
	navigation(
		route = AppRoute.MainGraph.routeId,
		startDestination = AppRoute.Main.routeId
	) {
		composable<AppRoute.Main> {

		}
		composable<AppRoute.Booking> {

		}
		composable<AppRoute.ShopCart> {

		}
		composable<AppRoute.Profile> {

		}
	}
}