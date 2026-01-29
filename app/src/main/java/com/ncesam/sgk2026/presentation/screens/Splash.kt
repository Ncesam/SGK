package com.ncesam.sgk2026.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.ncesam.sgk2026.presentation.navigation.AppNavigator
import com.ncesam.sgk2026.presentation.viewModels.SplashViewModel
import com.ncesam.uikit.foundation.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreenContent() {
	val gradient = Brush.linearGradient(
		colors = listOf(
			Color("#003D8E".toColorInt()),
			Color("#A371FF".toColorInt())
		),
		start = Offset(0f, Float.POSITIVE_INFINITY),
		end = Offset(Float.POSITIVE_INFINITY, 0f)
	)

	val colors = AppTheme.colors
	val typography = AppTheme.typography
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(gradient),
		contentAlignment = Alignment.Center
	) {
		BasicText(
			text = "Travel",
			color = { colors.white },
			style = typography.h1ExtraBold.copy(fontSize = 64.sp)
		)
	}
}


@Composable
fun SplashScreen(viewModel: SplashViewModel = koinViewModel()) {
	val navigator = AppNavigator.navigator
	val state by viewModel.state.collectAsState()
	if (state.isLoading) {
		SplashScreenContent()
	}

	LaunchedEffect(Unit) {
		viewModel.navigationEvent.collect { value ->
			navigator.navigate(value, true)
		}
	}

}