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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncesam.sgk2026.domain.navigation.AppRoute
import com.ncesam.sgk2026.domain.states.RegistrationEffect
import com.ncesam.sgk2026.domain.states.RegistrationEvent
import com.ncesam.sgk2026.domain.states.RegistrationState
import com.ncesam.sgk2026.presentation.navigation.AppNavigator
import com.ncesam.sgk2026.presentation.viewModels.RegistrationViewModel
import com.ncesam.uikit.components.AppBottomSheet
import com.ncesam.uikit.components.AppButton
import com.ncesam.uikit.components.AppButtonStyle
import com.ncesam.uikit.components.AppInput
import com.ncesam.uikit.components.AppSelect
import com.ncesam.uikit.components.AppSelectOption
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider
import com.ncesam.uikit.foundation.ScreenContext
import com.ncesam.uikit.foundation.ScreenProvider
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateProfileContent(state: RegistrationState, onEvent: (RegistrationEvent) -> Unit) {

	var firstNameFocused by remember { mutableStateOf(false) }
	var lastNameFocused by remember { mutableStateOf(false) }
	var fatherNameFocused by remember { mutableStateOf(false) }
	var bornFocused by remember { mutableStateOf(false) }
	var emailFocused by remember { mutableStateOf(false) }

	val options = listOf(
		AppSelectOption("Мужской", "male"), AppSelectOption("Женский", "female")
	)
	var genderBottomSheetExpanded by remember { mutableStateOf(false) }

	val scrollState = rememberScrollState()
	val colors = AppTheme.colors
	val typography = AppTheme.typography

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(colors.white)
			.verticalScroll(scrollState)
			.statusBarsPadding()
			.imePadding()
			.padding(horizontal = 20.dp, vertical = 32.dp),
		verticalArrangement = Arrangement.spacedBy(
			32.dp,
		),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Column(
			verticalArrangement = Arrangement.spacedBy(44.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			BasicText(text = "Создание Профиля", style = typography.h1ExtraBold)

			Column(
				verticalArrangement = Arrangement.spacedBy(8.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				BasicText(
					text = "Без профиля вы не сможете бронировать туры",
					style = typography.captionRegular,
					color = { colors.caption })
				BasicText(
					text = "В профиле будут храниться результаты поиска и забронированные туры",
					style = typography.captionRegular.copy(textAlign = TextAlign.Center),
					color = { colors.caption })
			}
		}

		AppInput(
			{ state -> firstNameFocused = state.isFocused },
			{ text -> onEvent(RegistrationEvent.FirstNameChanged(text)) },
			{},
			placeholder = "Имя",
			value = state.firstName,
			isFocused = firstNameFocused,
		)
		AppInput(
			{ state -> lastNameFocused = state.isFocused },
			{ text -> onEvent(RegistrationEvent.LastNameChanged(text)) },
			{},
			placeholder = "Фамилия",
			value = state.lastName,
			isFocused = lastNameFocused
		)
		AppInput(
			{ state -> fatherNameFocused = state.isFocused },
			{ text -> onEvent(RegistrationEvent.FatherNameChanged(text)) },
			{},
			placeholder = "Отчество",
			value = state.fatherName,
			isFocused = fatherNameFocused
		)
		AppInput(
			{ state -> bornFocused = state.isFocused },
			{ text -> onEvent(RegistrationEvent.BornChanged(text)) },
			{},
			placeholder = "Дата рождение",
			errorText = state.bornError,
			value = state.born,
			isFocused = bornFocused
		)
		AppSelect(
			selectedOption = options.find { option -> option.value == state.gender },
			placeholder = "Пол"
		) {
			genderBottomSheetExpanded = true
		}
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
								onEvent(RegistrationEvent.GenderChanged(action.value))
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
		AppInput(
			{ state -> emailFocused = state.isFocused },
			{ text -> onEvent(RegistrationEvent.EmailChanged(text)) },
			{},
			placeholder = "Почта",
			errorText = state.emailError,
			value = state.email,
			isFocused = emailFocused
		)
		Box(
			contentAlignment = Alignment.BottomCenter,
			modifier = Modifier.padding(top = 60.dp)
		) {
			AppButton(
				style = AppButtonStyle.Accent,
				content = "Далее",
				onClick = { onEvent(RegistrationEvent.GoToPassword) },
				enabled = state.isPasswordButtonEnabled
			)
		}
	}
}


@Composable
fun CreateProfileScreen(viewModel: RegistrationViewModel = koinViewModel()) {
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

	CreateProfileContent(state) { event -> scope.launch { viewModel.onEvent(event) } }

}


@Preview
@Composable
fun PreviewCreateProfile() {
	val state = RegistrationState()
	AppThemeProvider {
		ScreenProvider {
			CreateProfileContent(state) {

			}
		}
	}
}