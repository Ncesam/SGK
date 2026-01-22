package com.ncesam.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider


@Composable
fun AppCard(
    onClick: () -> Unit, content: @Composable () -> Unit
) {
    val colors = AppTheme.colors
    val shape = RoundedCornerShape(10.dp)
    Box(
        modifier = Modifier
            .background(colors.white, shape)
            .border(1.dp, colors.divider, shape)
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        content()
    }
}

@Composable
fun PrimaryAppCard(
    onClickBtn: () -> Unit,
    onClickCard: () -> Unit,
    added: Boolean = false,
    price: Int = 300,
    title: String = "Рубашка Воскресенье для машинного вязания",
    caption: String = "Мужская одежда",
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val buttonStyle = if (added) AppButtonStyle.Stroked else AppButtonStyle.Accent
    val buttonContent = if (added) "Убрать" else "Добавить"

    AppCard(onClickCard) {
        Column(modifier = Modifier.width(340.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                BasicText(text = title, style = typography.h3SemiBold, color = { colors.black })
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                ) {
                    BasicText(
                        text = caption,
                        style = typography.captionSemiBold,
                        color = { colors.caption })
                    BasicText(
                        text = price.toString() + "₽",
                        style = typography.h3SemiBold,
                        color = { colors.black })
                }

                AppButton(
                    style = buttonStyle,
                    size = AppButtonSize.Chip,
                    content = buttonContent,
                    onClick = onClickBtn
                )


            }
        }
    }
}


@Composable
fun CartAppCard(
    onClickCard: () -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    price: Int = 300,
    title: String = "Рубашка Воскресенье для машинного вязания",
    count: Int = 1,
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    AppCard(onClickCard) {
        Column(modifier = Modifier.width(340.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 34.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BasicText(text = title, style = typography.h3SemiBold, color = { colors.black })
                Icon(
                    painter = AppTheme.icons.Cross,
                    contentDescription = "Закрыть",
                    tint = colors.description,
                    modifier = Modifier.size(20.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                ) {
                    BasicText(
                        text = price.toString() + "₽",
                        style = typography.h3SemiBold,
                        color = { colors.black })
                }
                AppCounter(onIncrement, onDecrement, count)

            }
        }
    }
}

@Composable
fun ProjectAppCard(
    onClickBtn: () -> Unit,
    onClickCard: () -> Unit,
    title: String = "Рубашка Воскресенье для машинного вязания",
    caption: String = "Мужская одежда",
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography

    AppCard(onClickCard) {
        Column(modifier = Modifier.width(340.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 34.dp)
            ) {
                BasicText(text = title, style = typography.h3SemiBold, color = { colors.black })
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                ) {
                    BasicText(
                        text = caption,
                        style = typography.captionSemiBold,
                        color = { colors.caption })
                }
                AppButton(
                    onClick = onClickBtn,
                    style = AppButtonStyle.Accent,
                    size = AppButtonSize.Chip,
                    content = "Открыть"
                )


            }
        }
    }
}

@Preview
@Composable
fun PreviewCards() {
    AppThemeProvider {
        Column(verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically)) {
            PrimaryAppCard({}, {})
            PrimaryAppCard({}, {}, added = true)
            CartAppCard({}, {}, {})
            ProjectAppCard({}, {})
        }
    }

}
