package com.ncesam.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
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
fun AppCounter(
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    count: Int = 1,
    minValue: Int = 1,
) {
    val colors = AppTheme.colors
    val enabled = count > minValue
    val tint = if (enabled) colors.caption else colors.icons

    Row(
        horizontalArrangement = Arrangement.spacedBy(
            42.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicText(
            text = formatRussian(count),
            style = AppTheme.typography.textRegular,
            color = { colors.black },
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(colors.inputBackground, shape = RoundedCornerShape(8.dp))
                .padding(6.dp)
        ) {
            Icon(
                painter = AppTheme.icons.Minus,
                contentDescription = "Decrement",
                tint = tint,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onDecrement, enabled = enabled)
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(16.dp)
                    .background(colors.inputStroke)
            ) {}
            Icon(
                painter = AppTheme.icons.Plus,
                contentDescription = "Increment",
                tint = colors.caption,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onIncrement)
            )
        }
    }
}

private fun formatRussian(value: Int): String {
    val number = value.toString()
    return if (number.endsWith("1")) {
        "$number штука"
    } else if (number.endsWith("2") || number.endsWith("3") || number.endsWith("4")) {
        "$number штуки"
    } else {
        "$number штук"

    }
}

@Preview
@Composable
fun PreviewAppCounter() {
    AppThemeProvider {
        AppCounter({}, {})
    }
}