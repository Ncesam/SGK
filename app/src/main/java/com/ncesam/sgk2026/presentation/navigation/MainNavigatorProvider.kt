package com.ncesam.sgk2026.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ncesam.sgk2026.domain.navigation.AppRoute
import com.ncesam.sgk2026.domain.navigation.BottomTabs
import com.ncesam.sgk2026.domain.navigation.Navigator
import com.ncesam.sgk2026.presentation.screens.SplashScreen
import com.ncesam.uikit.components.AppNavigationBottomTab
import com.ncesam.uikit.components.AppNavigationBottomTabItem
import com.ncesam.uikit.foundation.AppTheme

val LocalNavigator = staticCompositionLocalOf<Navigator> {
	error("Wrap your app on MainNavigator")
}

val LocalBottomTabs = staticCompositionLocalOf<BottomTabs> {
	error("Wrap your app on MainNavigator")
}

@Composable
fun MainNavigator() {
	val navController = rememberNavController()
	val navigator = remember(navController) {
		MainNavigatorImpl(navController)
	}

	val backStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = backStackEntry?.destination
	val bottomTabs = remember { BottomTabs() }

	val colors = AppTheme.colors

	CompositionLocalProvider(
		LocalNavigator provides navigator,
		LocalBottomTabs provides bottomTabs
	) {
		NavHost(
			navController = navController,
			startDestination = AppRoute.Splash
		) {
			composable<AppRoute.Splash> {
				SplashScreen()
			}
			authGraph(navController)
		}
		if (bottomTabs.isVisible) {
			Row(
				modifier = Modifier
					.background(colors.white)
					.padding(bottom = 29.dp, end = 7.dp, start = 7.dp, top = 7.dp),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				AppNavigationBottomTab(
					item = AppNavigationBottomTabItem.Home,
					selected = currentDestination?.hierarchy?.any { it.route == AppRoute.Main.routeId } == true) {
					bottomTabs.show()
					navigator.navigate(AppRoute.Main)
				}
				AppNavigationBottomTab(
					item = AppNavigationBottomTabItem.Search,
					selected = currentDestination?.hierarchy?.any { it.route == AppRoute.Search.routeId } == true) {
					bottomTabs.show()
					navigator.navigate(AppRoute.Search)
				}
				AppNavigationBottomTab(
					item = AppNavigationBottomTabItem.Travels,
					selected = currentDestination?.hierarchy?.any { it.route == AppRoute.MyBooking.routeId } == true) {
					bottomTabs.show()
					navigator.navigate(AppRoute.MyBooking)
				}
				AppNavigationBottomTab(
					item = AppNavigationBottomTabItem.Profile,
					selected = currentDestination?.hierarchy?.any { it.route == AppRoute.Profile.routeId } == true) {
					bottomTabs.show()
					navigator.navigate(AppRoute.Profile)
				}

			}
		}
	}
}


object AppNavigator {
	val navigator: Navigator
		@Composable
		get() = LocalNavigator.current
}



