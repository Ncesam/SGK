package com.ncesam.sgk2026.presentation.navigation

import androidx.navigation.NavHostController
import com.ncesam.sgk2026.domain.navigation.AppRoute
import com.ncesam.sgk2026.domain.navigation.Navigator


class MainNavigatorImpl(private val navController: NavHostController) : Navigator {
	override fun navigate(
		route: AppRoute,
		clearAll: Boolean,
		clearToRoute: AppRoute?
	) {
		navController.navigate(route = route) {
			if (clearAll) {
				popUpTo(0)
			}
			if (clearToRoute != null) {
				popUpTo(clearToRoute) {
					inclusive = true
				}
			}
		}
	}

	override fun back() {
		navController.popBackStack()
	}

}