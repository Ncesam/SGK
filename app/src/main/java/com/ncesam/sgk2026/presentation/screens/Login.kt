package com.ncesam.sgk2026.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicText
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncesam.sgk2026.domain.navigation.AppRoute
import com.ncesam.sgk2026.domain.states.LoginEffect
import com.ncesam.sgk2026.domain.states.LoginEvent
import com.ncesam.sgk2026.domain.states.LoginState
import com.ncesam.sgk2026.presentation.navigation.AppNavigator
import com.ncesam.sgk2026.presentation.viewModels.LoginViewModel
import com.ncesam.uikit.components.AppButton
import com.ncesam.uikit.components.AppButtonOAuth
import com.ncesam.uikit.components.AppButtonSize
import com.ncesam.uikit.components.AppButtonStyle
import com.ncesam.uikit.components.AppButtonVariant
import com.ncesam.uikit.components.AppInput
import com.ncesam.uikit.components.AppInputType
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider
import com.ncesam.uikit.foundation.ScreenContext
import com.ncesam.uikit.foundation.ScreenProvider
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(
	viewModel: LoginViewModel = koinViewModel(),
) {
	val navigator = AppNavigator.navigator
	val showSnackBar = ScreenContext.showSnackBar
	LaunchedEffect(Unit) {
		viewModel.effect.collect { event ->
			when (event) {
				is LoginEffect.ShowSnackBar -> showSnackBar(event.msg)
				LoginEffect.NavigateToHome -> navigator.navigate(AppRoute.MainGraph)
				LoginEffect.NavigateToRegister -> navigator.navigate(AppRoute.CreateProfile)
			}
		}
	}
	val scope = rememberCoroutineScope()
	val state by viewModel.state.collectAsState()

	LoginContent(state) { event -> scope.launch { viewModel.onEvent(event) } }
}

@Composable
fun LoginContent(state: LoginState, onEvent: (LoginEvent) -> Unit) {
	val colors = AppTheme.colors
	val typography = AppTheme.typography


	var emailFocused by remember { mutableStateOf(false) }
	var passwordFocused by remember { mutableStateOf(false) }
	var visibilityPassword by remember { mutableStateOf(false) }
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(colors.white)
			.statusBarsPadding()
			.padding(horizontal = 20.dp, vertical = 60.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(60.dp)
	) {
		Column {
			BasicText(
				text = "Добро пожаловать!",
				style = typography.h1ExtraBold,
				modifier = Modifier.align(
					Alignment.CenterHorizontally
				)
			)
			BasicText(
				text = "Войдите, чтобы пользоваться функциями приложения",
				style = typography.textRegular.copy(textAlign = TextAlign.Center)
			)
		}
		Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
			Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
				AppInput(
					{ focus -> emailFocused = focus.isFocused },
					{ value -> onEvent(LoginEvent.EmailChanged(value)) },
					{},
					type = AppInputType.Text,
					placeholder = "example@mail.com",
					helperText = "Вход по E-Mail",
					errorText = state.emailError,
					value = state.email,
					isFocused = emailFocused,
				)

				AppInput(
					{ focus -> passwordFocused = focus.isFocused },
					{ value -> onEvent(LoginEvent.PasswordChanged(value)) },
					{ visibilityPassword = !visibilityPassword },
					placeholder = "",
					helperText = "Пароль",
					errorText = state.passwordError,
					value = state.password,
					isFocused = passwordFocused,
					isVisiblePassword = visibilityPassword,
					type = AppInputType.Password
				)
			}
			AppButton(
				style = AppButtonStyle.Accent,
				content = "Далее",
				size = AppButtonSize.Big,
				onClick = { onEvent(LoginEvent.LoginClicked) },
				enabled = state.isButtonEnabled
			)
			BasicText(
				text = "Зарегистрироваться",
				modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.clickable { onEvent(LoginEvent.RegisterClicked) },
				style = typography.textRegular.copy(
					textAlign = TextAlign.Center, color = colors.accent
				)
			)
		}
		Column(
			verticalArrangement = Arrangement.spacedBy(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {

			BasicText(
				"Или продолжите с помощью",
				style = typography.textRegular,
				color = { colors.caption },
				modifier = Modifier.align(
					Alignment.CenterHorizontally
				)
			)
			Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
				AppButtonOAuth(variant = AppButtonVariant.Yandex) {
					onEvent(LoginEvent.OAuthClicked)
				}
				AppButtonOAuth(variant = AppButtonVariant.VK) {
					onEvent(LoginEvent.OAuthClicked)
				}
			}
		}


	}
}

@Preview
@Composable
fun PreviewLoginContent() {
	AppThemeProvider {
		ScreenProvider {
			LoginContent(LoginState()) { }
		}
	}
}