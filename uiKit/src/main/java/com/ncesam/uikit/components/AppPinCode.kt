package com.ncesam.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider


@Composable
fun AppPinCode(onAdd: (number: String) -> Unit, onDelete: () -> Unit) {
	val colors = AppTheme.colors
	val typography = AppTheme.typography

	val shape = RoundedCornerShape(50)
	val gridState = rememberLazyGridState()
	val pinCodeNumbersList = (1..9).toList()
	LazyVerticalGrid(
		columns = GridCells.Fixed(3),
		state = gridState,
		verticalArrangement = Arrangement.spacedBy(24.dp),
		horizontalArrangement = Arrangement.spacedBy(24.dp),
	) {
		items(items = pinCodeNumbersList) { i ->
			val interactionSource = remember { MutableInteractionSource() }
			val isPressed by interactionSource.collectIsPressedAsState()
			Box(
				modifier = Modifier
					.aspectRatio(1f)
					.background(
						if (isPressed) colors.accent else colors.inputBackground,
						shape = shape
					)
					.clickable(interactionSource = interactionSource, indication = null) {
						onAdd(i.toString())
					},
				contentAlignment = Alignment.Center
			) {
				BasicText(
					text = i.toString(),
					style = typography.h1ExtraBold,
					color = { if (isPressed) colors.white else colors.black })
			}
		}
		item { Spacer(modifier = Modifier.aspectRatio(1f)) }
		item {
			val interactionSource = remember { MutableInteractionSource() }
			val isPressed by interactionSource.collectIsPressedAsState()
			Box(
				modifier = Modifier
					.aspectRatio(1f)
					.background(
						if (isPressed) colors.accent else colors.inputBackground,
						shape = shape
					)
					.clickable(interactionSource = interactionSource, indication = null) {
						onAdd(0.toString())
					},
				contentAlignment = Alignment.Center
			) {
				BasicText(
					text = 0.toString(),
					style = typography.h1ExtraBold,
					color = { if (isPressed) colors.white else colors.black })
			}
		}
	}
}

`````c
@Preview
@Composable
fun PreviewAppPinCode() {
	AppThemeProvider {
		AppPinCode({}) { }
	}
}