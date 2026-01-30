package com.ncesam.sgk2026.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncesam.sgk2026.domain.navigation.AppRoute
import com.ncesam.sgk2026.domain.states.RegistrationEffect
import com.ncesam.sgk2026.domain.states.RegistrationEvent
import com.ncesam.sgk2026.domain.states.RegistrationState
import com.ncesam.sgk2026.presentation.navigation.AppNavigator
import com.ncesam.sgk2026.presentation.viewModels.RegistrationViewModel
import com.ncesam.uikit.components.AppButton
import com.ncesam.uikit.components.AppButtonStyle
import com.ncesam.uikit.components.AppInput
import com.ncesam.uikit.components.AppInputType
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider
import com.ncesam.uikit.foundation.ScreenContext
import com.ncesam.uikit.foundation.ScreenProvider
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


data class InputState(
	val passwordFocused: Boolean = false,
	val visiblePassword: Boolean = false,
	val retryPasswordFocused: Boolean = false,
	val visibleRetryPassword: Boolean = false,
)

@Composable
fun CreatePasswordContent(state: RegistrationState, onEvent: (RegistrationEvent) -> Unit) {
	var inputState by remember { mutableStateOf(InputState()) }

	val colors = AppTheme.colors
	val typography = AppTheme.typography
	val roundedShape = RoundedCornerShape(10.dp)

	val scrollState = rememberScrollState()

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(colors.white)
			.verticalScroll(scrollState)
			.statusBarsPadding()
			.imePadding()
			.padding(top = 59.dp, start = 20.dp, end = 20.dp),
		verticalArrangement = Arrangement.spacedBy(60.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Column(
			verticalArrangement = Arrangement.spacedBy(23.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			BasicText(text = "Создание Профиля", style = typography.h1ExtraBold)
			BasicText(text = "Введите новый пароль", style = typography.captionRegular)
		}
		Column(
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			AppInput(
				{ state -> inputState = inputState.copy(passwordFocused = state.isFocused) },
				{ text -> onEvent(RegistrationEvent.PasswordChanged(text)) },
				{ inputState = inputState.copy(visiblePassword = !inputState.visiblePassword) },
				placeholder = "*********",
				value = state.password,
				isFocused = inputState.passwordFocused,
				isVisiblePassword = inputState.visiblePassword,
				helperText = "Новый пароль",
				errorText = state.passwordError,
				type = AppInputType.Password
			)
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.shadow(1.dp, roundedShape)
					.background(colors.white, roundedShape)
					.border(1.dp, colors.inputStroke, roundedShape)
					.padding(16.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp),
				horizontalAlignment = Alignment.Start
			) {
				BasicText(text = "Требования к паролю", style = typography.captionSemiBold)
				BasicText(
					text = "• Минимум 8 символов",
					style = typography.caption2Regular,
					color = { if (state.password.isBlank()) colors.black else if (state.rulesPassword.minLength) colors.success else colors.error })
				BasicText(
					text = "• Заглавная буква",
					style = typography.caption2Regular,
					color = { if (state.password.isBlank()) colors.black else if (state.rulesPassword.hasUpper) colors.success else colors.error })
				BasicText(
					text = "• Строчная буква",
					style = typography.caption2Regular,
					color = { if (state.password.isBlank()) colors.black else if (state.rulesPassword.hasLower) colors.success else colors.error })
				BasicText(
					text = "• Цифра",
					style = typography.caption2Regular,
					color = { if (state.password.isBlank()) colors.black else if (state.rulesPassword.hasDigit) colors.success else colors.error })
				BasicText(
					text = "• Спецсимвол (!@#\$)",
					style = typography.caption2Regular,
					color = { if (state.password.isBlank()) colors.black else if (state.rulesPassword.hasSpecial) colors.success else colors.error })

			}
			AppInput(
				{ state -> inputState = inputState.copy(retryPasswordFocused = state.isFocused) },
				{ text -> onEvent(RegistrationEvent.RetryPasswordChanged(text)) },
				{
					inputState =
						inputState.copy(visibleRetryPassword = !inputState.visibleRetryPassword)
				},
				placeholder = "*********",
				value = state.retryPassword,
				isFocused = inputState.retryPasswordFocused,
				isVisiblePassword = inputState.visibleRetryPassword,
				helperText = "Повторите пароль",
				type = AppInputType.Password
			)
			AppButton(
				style = AppButtonStyle.Accent,
				content = "Сохранить",
				onClick = { onEvent(RegistrationEvent.GoToPinCode) },
				enabled = state.rulesPassword.isValid() && state.isPasswordRetryEqual
			)
		}

	}

}


@Composable
fun CreatePasswordScreen(viewModel: RegistrationViewModel = koinViewModel()) {
	val navigator = AppNavigator.navigator
	val showSnackBar = ScreenContext.showSnackBar
	val state by viewModel.state.collectAsState()
	val scope = rememberCoroutineScope()

	LaunchedEffect(Unit) {
		viewModel.effect.collect { effect ->
			when (effect) {
				is RegistrationEffect.ShowSnackBar -> showSnackBar(effect.msg)
				RegistrationEffect.NavigateToCreatePasswordScreen -> navigator.navigate(AppRoute.CreatePassword)
				RegistrationEffect.NavigateToCreatePinCodeScreen -> navigator.navigate(AppRoute.CreatePinCode)
				RegistrationEffect.NavigateToLogin -> navigator.navigate(
					AppRoute.Login,
					clearToRoute = AppRoute.Login
				)
			}
		}
	}

	CreatePasswordContent(state) { event -> scope.launch { viewModel.onEvent(event) } }

}


@Preview
@Composable
fun PreviewCreatePassword() {
	val state = RegistrationState()
	AppThemeProvider {
		ScreenProvider {
			CreatePasswordContent(state) {

			}
		}
	}
}