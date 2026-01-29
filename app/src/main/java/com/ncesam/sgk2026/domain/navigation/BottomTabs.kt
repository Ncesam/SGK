package com.ncesam.sgk2026.domain.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class BottomTabs(isVisible: Boolean = false) {
	var isVisible by mutableStateOf(isVisible)

	fun show() {
		isVisible = true
	}

	fun hide() {
		isVisible = false
	}
}