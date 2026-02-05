package com.ncesam.sgk2026.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavDestination.Companion.hasRoute
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
		Column(modifier = Modifier.fillMaxSize()) {
			Box(modifier = Modifier.weight(1f)) {
				NavHost(
					navController = navController,
					startDestination = AppRoute.Splash
				) {
					composable<AppRoute.Splash> {
						SplashScreen()
					}
					authGraph(navController)
					mainGraph()
				}
			}
			if (bottomTabs.isVisible) {
				Row(
					modifier = Modifier
						.background(colors.white)
						.fillMaxWidth()
						.drawBehind {
							val h = 1.dp.toPx()
							drawRect(
								brush = Brush.verticalGradient(
									listOf(Color("#A0A0A0".toColorInt()), Color.Transparent),
									tileMode = TileMode.Decal
								),
								size = Size(size.width, h)
							)
						}
						.padding(15.dp),
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					AppNavigationBottomTab(
						item = AppNavigationBottomTabItem.Home,
						selected = currentDestination?.hierarchy?.any { route -> route.hasRoute<AppRoute.Main>() } == true) {
						bottomTabs.show()
						navigator.navigate(AppRoute.Main)
					}
					AppNavigationBottomTab(
						item = AppNavigationBottomTabItem.Search,
						selected = currentDestination?.hierarchy?.any { route -> route.hasRoute<AppRoute.Search>() } == true) {
						bottomTabs.show()
						navigator.navigate(AppRoute.Search(""))
					}
					AppNavigationBottomTab(
						item = AppNavigationBottomTabItem.Travels,
						selected = currentDestination?.hierarchy?.any { route -> route.hasRoute<AppRoute.MyBooking>() } == true) {
						bottomTabs.show()
						navigator.navigate(AppRoute.MyBooking)
					}
					AppNavigationBottomTab(
						item = AppNavigationBottomTabItem.Profile,
						selected = currentDestination?.hierarchy?.any { route -> route.hasRoute<AppRoute.Profile>() } == true) {
						bottomTabs.show()
						navigator.navigate(AppRoute.Profile)
					}

				}
			}
		}
	}
}


object AppNavigator {
	val navigator: Navigator
		@Composable
		get() = LocalNavigator.current

	val bottomTabs: BottomTabs
		@Composable
		get() = LocalBottomTabs.current
}



