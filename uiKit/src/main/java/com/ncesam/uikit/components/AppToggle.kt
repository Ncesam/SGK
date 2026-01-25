package com.ncesam.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider


@Composable
fun AppToggle(
	enabled: Boolean = false,
	onToggle: () -> Unit,
) {
	val colors = AppTheme.colors
	val shape = RoundedCornerShape(50)
	val modifier = Modifier
		.graphicsLayer(shape = shape, clip = true)
		.background(if (enabled) colors.accent else colors.inputStroke)
		.clickable { onToggle() }
		.padding(
			start = if (enabled) 22.dp else 2.dp,
			top = 2.dp,
			end = if (enabled) 2.dp else 22.dp,
			bottom = 2.dp
		)
	Row(modifier = modifier) {
		Box(
			modifier = Modifier
				.size(24.dp)
				.graphicsLayer(shape = shape, clip = true)
				.background(colors.white)
		)
	}

}

@Preview
@Composable
fun PreviewAppToggle() {
	AppThemeProvider {
		Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
			AppToggle(true) { }
			AppToggle(false) { }
		}
	}
}