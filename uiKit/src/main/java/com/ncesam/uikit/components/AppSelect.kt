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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppTheme.colors
import com.ncesam.uikit.foundation.AppTheme.typography
import com.ncesam.uikit.foundation.AppThemeProvider

@Composable
fun AppSelect(
	placeholder: String = "Пол",
	selectedOption: AppSelectOption? = null,
	onClick: () -> Unit,
) {
	val colors = AppTheme.colors
	val shape = RoundedCornerShape(10.dp)
	val typography = AppTheme.typography


	Box {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.background(colors.inputBackground, shape)
				.border(
					width = 1.dp,
					color = if (selectedOption != null) colors.icons else colors.inputStroke,
					shape = shape
				)
				.padding(14.dp)
				.clickable { onClick() },
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
					.size(20.dp),
			)
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
	var selectedOption by remember { mutableStateOf<AppSelectOption?>(null) }
	var genderBottomSheetExpanded by remember { mutableStateOf(false) }
	AppThemeProvider {
		Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
			AppSelect(
				selectedOption = selectedOption,
				placeholder = "Пол"
			) {}
			AppBottomSheet(
				{ genderBottomSheetExpanded = false },
				isVisible = genderBottomSheetExpanded,
				name = "Пол"
			) {
				Column(verticalArrangement = Arrangement.spacedBy(14.dp), modifier = Modifier) {
					options.forEach { action ->
						Row(
							modifier = Modifier
								.fillMaxWidth()
								.border(1.dp, colors.inputStroke, RoundedCornerShape(10.dp))
								.padding(12.dp)
								.clickable {
									selectedOption = action
									genderBottomSheetExpanded = false
								}) {
							BasicText(
								text = action.label,
								style = typography.headlineRegular,
								modifier = Modifier
							)
						}
					}
				}
			}
		}
	}
}