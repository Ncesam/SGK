package com.ncesam.sgk2026.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.ncesam.sgk2026.domain.navigation.AppRoute
import com.ncesam.sgk2026.domain.states.RegistrationEffect
import com.ncesam.sgk2026.domain.states.RegistrationEvent
import com.ncesam.sgk2026.domain.states.RegistrationState
import com.ncesam.sgk2026.presentation.navigation.AppNavigator
import com.ncesam.sgk2026.presentation.viewModels.RegistrationViewModel
import com.ncesam.uikit.components.AppPinCode
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider
import com.ncesam.uikit.foundation.ScreenContext
import com.ncesam.uikit.foundation.ScreenProvider
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun CreatePinCodeContent(state: RegistrationState, onEvent: (RegistrationEvent) -> Unit) {
	val colors = AppTheme.colors
	val typography = AppTheme.typography

	val shape = RoundedCornerShape(50)
	val showBiometric = state.pinCode.length == 4

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(colors.white)
			.statusBarsPadding()
			.padding(top = 59.dp, start = 20.dp, end = 20.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(60.dp)
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			BasicText("Создайте пароль", style = typography.h1ExtraBold)
			BasicText(
				"Для защиты ваших персональных данных",
				style = typography.captionRegular,
				color = { colors.caption })
		}

		Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
			for (i in 1..4) {
				Box(
					modifier = Modifier
						.size(16.dp)
						.border(1.dp, colors.accent, shape)
						.background(
							color = if (state.pinCode.length >= i) colors.accent else colors.white,
							shape = shape
						)
				) {}
			}
		}
		AppPinCode(
			onAdd = { number -> onEvent(RegistrationEvent.AddSymbolPinCode(number)) },
			onDelete = { onEvent(RegistrationEvent.DeleteSymbolPinCode) })
		if (showBiometric) {
			Popup(alignment = Alignment.Center, properties = PopupProperties(focusable = true)) {
				Column(
					modifier = Modifier
						.padding(30.dp)
						.shadow(1.dp, RoundedCornerShape(12.dp))
						.background(colors.white, RoundedCornerShape(12.dp))
						.padding(10.dp),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					BasicText(
						text = "Вы хотите использовать биометрию?",
						style = typography.h1ExtraBold.copy(textAlign = TextAlign.Center)
					)
					Row(
						horizontalArrangement = Arrangement.SpaceBetween,
						modifier = Modifier.fillMaxWidth()
					) {
						BasicText(
							modifier = Modifier
								.background(
									colors.accent, RoundedCornerShape(10.dp)
								)
								.padding(16.dp)
								.clickable { onEvent(RegistrationEvent.UseBiometricChanged(true)) },
							text = "Да",
							style = typography.h1ExtraBold,
							color = { colors.white })
						BasicText(
							text = "Нет",
							style = typography.h1ExtraBold,
							modifier = Modifier
								.background(
									colors.inputBackground,
									RoundedCornerShape(10.dp)
								)
								.padding(16.dp)
								.clickable {
									onEvent(
										RegistrationEvent.UseBiometricChanged(false)
									)
								})
					}
				}
			}
		}
	}
}

@Composable
fun CreatePinCodeScreen(viewModel: RegistrationViewModel = koinViewModel()) {
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
					AppRoute.Login, clearToRoute = AppRoute.Login
				)
			}
		}
	}

	CreatePinCodeContent(state) { event -> scope.launch { viewModel.onEvent(event) } }
}

@Preview
@Composable
fun PreviewCreatePinCode() {
	val state = RegistrationState()
	AppThemeProvider {
		ScreenProvider {
			CreatePinCodeContent(state) {

			}
		}
	}
}