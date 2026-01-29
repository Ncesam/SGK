package com.ncesam.uikit.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
	onDismiss: () -> Unit,
	isVisible: Boolean = false,
	name: String = "Example",
	content: @Composable () -> Unit
) {
	val colors = AppTheme.colors
	val typography = AppTheme.typography
	if (isVisible) {
		ModalBottomSheet(
			onDismissRequest = onDismiss,
			contentColor = colors.white,
			containerColor = colors.white
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.background(
						color = colors.white,
						shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
					)
					.pointerInput(
						Unit
					) {
						detectVerticalDragGestures(onVerticalDrag = { change, dragAmount ->
							if (dragAmount > 10) {
								onDismiss()
							}
							change.consume()
						})
					}
					.clickable(
						onClick = {},
						indication = null,
						interactionSource = remember { MutableInteractionSource() }),
				contentAlignment = Alignment.BottomCenter
			) {
				Column(
					modifier = Modifier
						.padding(20.dp)
						.defaultMinSize(minHeight = 170.dp)
						.animateContentSize(),
					verticalArrangement = Arrangement.spacedBy(16.dp)
				) {
					Row(
						modifier = Modifier.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically
					) {
						BasicText(text = name, style = typography.h2ExtraBold)
						Icon(
							painter = AppTheme.icons.CrossRounded,
							contentDescription = null,
							modifier = Modifier.clickable(
								indication = null,
								interactionSource = remember { MutableInteractionSource() }) { onDismiss() },
							tint = colors.icons,
						)
					}
					Box(
						modifier = Modifier
							.width(340.dp)
							.height(1.dp)
							.background(colors.divider)
					)
					content()
				}
			}
		}
	}

}


@Preview
@Composable
fun PreviewInteractiveBottomSheet() {
	AppThemeProvider {
		var showSheet by remember { mutableStateOf(true) }
		AppTheme.colors
		AppBottomSheet({ showSheet = false }, isVisible = showSheet, name = "Hotel Plaza") {

		}
	}
}