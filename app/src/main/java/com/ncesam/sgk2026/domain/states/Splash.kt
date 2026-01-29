package com.ncesam.sgk2026.domain.states

import com.ncesam.sgk2026.domain.navigation.AppRoute

data class SplashState(
	val isLoading: Boolean = true,
	val startDestination: AppRoute? = null
)

