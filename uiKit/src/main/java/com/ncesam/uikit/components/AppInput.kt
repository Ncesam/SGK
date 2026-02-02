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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.wear.compose.material.Icon
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider


@Composable
fun AppInput(
	onFocusChanged: (FocusState) -> Unit,
	onChangeText: (textFieldValue: String) -> Unit,
	onClickPasswordVisibility: () -> Unit,
	placeholder: String = "Пример",
	helperText: String? = null,
	errorText: String? = null,
	value: String = "",
	isFocused: Boolean = false,
	isVisiblePassword: Boolean = false,
	type: AppInputType = AppInputType.Text
) {
	val colors = AppTheme.colors
	val typography = AppTheme.typography
	// Boolean Values
	val isFilled = value != ""
	val isError = errorText != null
	val isPassword = type == AppInputType.Password

	val icon = if (!isVisiblePassword) AppTheme.icons.ClosedEye else AppTheme.icons.OpenEye
	val passwordTransformation =
		if (isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation()
	// Helpers for styling
	val backgroundColor = if (!isError) colors.inputBackground else Color("#10FD3535".toColorInt())
	val borderColor =
		if (isError) colors.error else if (isFocused) colors.accent else if (isFilled) colors.icons else colors.inputStroke
	val shape = RoundedCornerShape(10.dp)
	Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
		if (helperText != null) {
			BasicText(
				text = helperText,
				style = typography.textRegular,
				color = { colors.description })
		}
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.background(backgroundColor, shape)
				.border(1.dp, borderColor, shape)
				.padding(14.dp)
		) {
			BasicTextField(
				value = value,
				onValueChange = onChangeText,
				modifier = Modifier
					.onFocusChanged(onFocusChanged = onFocusChanged)
					.weight(1f),
				visualTransformation = if (isPassword) passwordTransformation else VisualTransformation.None,
				textStyle = typography.headlineRegular,
				decorationBox = { innerTextField ->
					Box(modifier = Modifier, contentAlignment = Alignment.CenterStart) {
						if (!isFilled) {
							BasicText(
								text = placeholder,
								style = typography.headlineRegular,
								color = { colors.caption })
						}
						innerTextField()
					}
				}
			)
			if (isPassword) {
				Icon(
					painter = icon,
					contentDescription = "Переключатель видимости пароля",
					tint = colors.black,
					modifier = Modifier
						.size(20.dp)
						.clickable(onClick = onClickPasswordVisibility)
				)
			}
		}
		if (isError) {
			BasicText(errorText, style = typography.textRegular, color = { colors.error })
		}
	}

}


@Composable
fun AppInputSearch(
	onFocusChanged: (FocusState) -> Unit,
	onChangeText: (textFieldValue: String) -> Unit,
	placeholder: String = "Поиск",
	helperText: String? = null,
	value: String = "",
	isFocused: Boolean = false,
	modifier: Modifier = Modifier
) {
	val colors = AppTheme.colors
	val typography = AppTheme.typography
	// Boolean Values
	val isFilled = value != ""

	val icon = AppTheme.icons.Search
	// Helpers for styling
	val borderColor = if (isFocused) colors.accent else colors.inputStroke
	val shape = RoundedCornerShape(10.dp)
	Column(
		modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		if (helperText != null) {
			BasicText(
				text = helperText,
				style = typography.textRegular,
				color = { colors.description })
		}
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.background(colors.inputBackground, shape)
				.border(1.dp, borderColor, shape)
				.padding(14.dp),
			horizontalArrangement = Arrangement.spacedBy(8.dp)
		) {
			Icon(
				painter = icon,
				contentDescription = null,
				modifier = Modifier.size(20.dp),
				tint = colors.description
			)
			BasicTextField(
				value = value,
				onValueChange = onChangeText,
				modifier = Modifier
					.onFocusChanged(onFocusChanged = onFocusChanged)
					.weight(1f),
				textStyle = typography.headlineRegular,
				decorationBox = { innerTextField ->
					Box(modifier = Modifier, contentAlignment = Alignment.CenterStart) {
						if (!isFilled) {
							BasicText(
								text = placeholder,
								style = typography.headlineRegular,
								color = { colors.caption })
						}
						innerTextField()
					}
				}
			)
		}
	}
}

enum class AppInputType {
	Password, Text, Data
}


@Preview(showBackground = true)
@Composable
fun PreviewAppInput() {
	var inputFocused by remember { mutableStateOf(true) }
	var inputVisibility by remember { mutableStateOf(false) }
	var inputValue by remember { mutableStateOf("") }
	AppThemeProvider {
		Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
			AppInput(
				{ focusState -> inputFocused = focusState.isFocused },
				{ text -> inputValue = text },
				{ inputVisibility = !inputVisibility },
				value = inputValue,
				placeholder = "Пароль",
				helperText = "Пароль",
				type = AppInputType.Password,
				isFocused = inputFocused,
				isVisiblePassword = inputVisibility
			)
			Row {
				AppInputSearch(
					{ focusState -> inputFocused = focusState.isFocused },
					{ text -> inputValue = text },
					value = inputValue,
					placeholder = "Поиск",
					isFocused = inputFocused,
				)
				Icon(
					painter = AppTheme.icons.Profile,
					tint = AppTheme.colors.icons,
					contentDescription = null,
					modifier = Modifier.size(32.dp)
				)
			}
		}
	}
}