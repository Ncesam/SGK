package com.ncesam.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider


@Composable
fun AppSnackBar(
	message: String = "Возникла какая-то ошибка(",
	onDismiss: () -> Unit,
) {
	val colors = AppTheme.colors
	val typography = AppTheme.typography
	val shape = RoundedCornerShape(8.dp)
	Box(modifier = Modifier.width(380.dp).shadow(1.dp, shape).background(colors.white, shape)) {
		Icon(
			painter = AppTheme.icons.CrossRounded,
			modifier = Modifier
				.align(Alignment.TopEnd)
				.padding(8.dp)
				.size(24.dp)
				.clickable { onDismiss() },
			tint = colors.icons,
			contentDescription = "Закрыть уведомление"
		)
		Box(
			modifier = Modifier
				.padding(horizontal = 20.dp, vertical = 40.dp),
			contentAlignment = Alignment.Center
		) {
			BasicText(message, style = typography.h2ExtraBold, color = { colors.black })
		}
	}
}


@Preview
@Composable
fun PreviewAppSnackBar() {
	AppThemeProvider {
		AppSnackBar{}
	}
}