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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.wear.compose.material.Icon
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider

@Composable
fun AppSelect(
    placeholder: String = "Пол",
    options: List<AppSelectOption>,
    onOptionSelected: (option: AppSelectOption) -> Unit,
    selectedOption: AppSelectOption? = null,
) {
    val colors = AppTheme.colors
    val shape = RoundedCornerShape(10.dp)
    val typography = AppTheme.typography

    var expanded by remember { mutableStateOf(false) }

    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(colors.inputBackground, shape)
                .border(
                    width = 1.dp,
                    color = if (selectedOption != null) colors.icons else colors.inputStroke,
                    shape = shape
                )
                .padding(14.dp)
                .clickable { expanded = !expanded },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BasicText(
                text = selectedOption?.label ?: placeholder,
                style = typography.headlineRegular,
                color = { if (selectedOption != null) colors.black else colors.description })
            Icon(
                painter = AppTheme.icons.ArrowDown,
                tint = colors.description,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .graphicsLayer(rotationZ = if (expanded) 0f else 90f),
            )
        }
        if (expanded) {
            Popup(alignment = Alignment.Center, offset = IntOffset(0, 150), { expanded = false }) {
                Column {
                    options.forEach { action ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(color = colors.inputBackground, shape = shape)
                                .clickable {
                                    onOptionSelected(action)
                                    expanded = false
                                }) {
                            BasicText(text = action.label, modifier = Modifier)
                        }
                    }
                }
            }
        }
    }


}


data class AppSelectOption(
    val label: String,
    val value: String,
)

@Preview
@Composable
fun PreviewAppSelect() {
    val options: List<AppSelectOption> =
        listOf(AppSelectOption("Мужской", "male"), AppSelectOption("Женский", "female"))
    var selectedOption: AppSelectOption? = null
    AppThemeProvider {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            AppSelect(
                placeholder = "Пол",
                options = options,
                onOptionSelected = { option -> selectedOption = option },
                selectedOption = selectedOption
            )
        }
    }
}