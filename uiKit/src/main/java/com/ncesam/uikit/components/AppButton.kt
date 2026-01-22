package com.ncesam.uikit.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider


@Composable
fun AppButton(
    style: AppButtonStyle = AppButtonStyle.Default,
    size: AppButtonSize = AppButtonSize.Big,
    content: String = "Example",

    onClick: () -> Unit,
    enabled: Boolean = true
) {
    val colors = AppTheme.colors
    val shape = RoundedCornerShape(10.dp)
    var modifier = if (enabled) when (style) {
        AppButtonStyle.Accent -> Modifier.background(colors.accent, shape)
        AppButtonStyle.Stroked -> Modifier.background(colors.white, shape)
        AppButtonStyle.Default -> Modifier.background(colors.inputBackground, shape)
    } else Modifier.background(colors.accentInactive, shape)
    modifier = if (style == AppButtonStyle.Stroked) modifier.border(
        1.dp,
        colors.accent,
        shape
    ) else modifier

    val textColor = ColorProducer {
        val color = if (enabled) when (style) {
            AppButtonStyle.Stroked -> colors.accent
            AppButtonStyle.Default -> colors.black
            AppButtonStyle.Accent -> colors.white
        } else colors.white
        color
    }
    val width = when (size) {
        AppButtonSize.Big -> 340.dp
        AppButtonSize.Small -> 100.dp
        AppButtonSize.Chip -> 140.dp
    }
    val verticalPadding = when (size) {
        AppButtonSize.Small -> 8.dp
        AppButtonSize.Big -> 15.dp
        AppButtonSize.Chip -> 10.dp
    }
    val font = when (size) {
        AppButtonSize.Small -> AppTheme.typography.captionSemiBold
        AppButtonSize.Chip -> AppTheme.typography.captionSemiBold
        AppButtonSize.Big -> AppTheme.typography.h3SemiBold
    }
    Row(
        modifier = modifier
            .clickable(enabled = enabled, onClick = onClick)
            .width(width)
            .padding(vertical = verticalPadding),
        Arrangement.Center, Alignment.CenterVertically

    ) {
        BasicText(
            text = content,
            style = font,
            color = textColor,
            maxLines = 1
        )
    }

}

@Composable
fun ToCartAppButton(
    onClick: () -> Unit,
    total: Int = 500
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    Row(
        Modifier
            .width(340.dp)
            .background(color = colors.accent, shape = RoundedCornerShape(10.dp))
            .padding(16.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(
                painter = AppTheme.icons.ShopCart,
                contentDescription = "Перейти к корзине",
                tint = colors.white,
                modifier = Modifier.size(20.dp)
            )
            BasicText("В корзину", Modifier, typography.h3SemiBold, color = { colors.white })
        }

        BasicText(total.toString() + "₽", Modifier, typography.h2SemiBold, color = { colors.white })
    }
}

@Composable
fun BackAppButton(
    onClick: () -> Unit
) {
    Icon(
        painter = AppTheme.icons.ArrowLeft,
        contentDescription = "Вернуться",
        modifier = Modifier
            .background(AppTheme.colors.inputBackground, RoundedCornerShape(8.dp))
            .padding(6.dp)
            .clickable(onClick = onClick),
        tint = AppTheme.colors.description
    )
}

@Composable
fun FilterAppButton(
    onClick: () -> Unit
) {
    Icon(
        painter = AppTheme.icons.Filter,
        contentDescription = "Фильтры",
        modifier = Modifier
            .background(AppTheme.colors.inputBackground, RoundedCornerShape(8.dp))
            .padding(14.dp)
            .clickable(onClick = onClick),
        tint = AppTheme.colors.description
    )
}

@Preview
@Composable
fun AppButtonPreview() {
    AppThemeProvider {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            AppButton(
                style = AppButtonStyle.Default,
                size = AppButtonSize.Chip,
                onClick = { print("3") }, enabled = true
            )
            AppButton(
                style = AppButtonStyle.Stroked,
                size = AppButtonSize.Big,
                onClick = { print("3") }, enabled = true
            )
            AppButton(
                style = AppButtonStyle.Accent,
                size = AppButtonSize.Small,
                onClick = { print("3") }, enabled = true
            )
            ToCartAppButton({})
            BackAppButton {}
            FilterAppButton {}
        }
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