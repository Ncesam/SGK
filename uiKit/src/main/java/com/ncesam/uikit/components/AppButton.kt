package com.ncesam.uikit.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider


@Composable
fun AppButton(
    style: AppButtonStyle = AppButtonStyle.Default,
    size: AppButtonSize = AppButtonSize.Big,
    content: String = "Example",
    horizontalPadding: Dp = 60.dp,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    val colors = AppTheme.colors
    val backgroundColor = when (style) {
        AppButtonStyle.Accent -> colors.accent
        AppButtonStyle.Stroked -> colors.white
        AppButtonStyle.Default -> colors.inputBackground
    }
    val borderWidth = if (style == AppButtonStyle.Stroked) 1.dp else 0.dp
    val textColor = ColorProducer {
        val color = when (style) {
            AppButtonStyle.Stroked -> colors.accent
            AppButtonStyle.Default -> colors.black
            AppButtonStyle.Accent -> colors.white
        }
        color
    }
    Row(
        modifier = Modifier
            .clickable(enabled = enabled, onClick = onClick)
            .background(backgroundColor)
            .border(width = borderWidth, color = colors.accent)
            .fillMaxWidth(),
        Arrangement.Center, Alignment.CenterVertically

    ) {
        BasicText(
            text = content,
            style = AppTheme.typography.h3SemiBold,
            color = textColor,
            maxLines = 1
        )
    }

}

@Preview
@Composable
fun AppButtonPreview() {
    AppThemeProvider {
        AppButton(onClick = { print("3") })
    }
}

enum class AppButtonStyle {
    Accent,
    Stroked,
    Default
}

enum class AppButtonSize {
    Big, Chip, Small
}