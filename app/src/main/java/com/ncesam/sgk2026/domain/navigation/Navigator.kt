package com.ncesam.sgk2026.domain.navigation

interface Navigator {
	fun navigate(route: AppRoute, clearAll: Boolean = false, clearToRoute: AppRoute? = null)
	fun back()
}