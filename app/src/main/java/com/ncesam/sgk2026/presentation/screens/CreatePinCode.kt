package com.ncesam.sgk2026.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncesam.sgk2026.domain.states.RegistrationEvent
import com.ncesam.sgk2026.domain.states.RegistrationState
import com.ncesam.sgk2026.presentation.viewModels.RegistrationViewModel
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider
import com.ncesam.uikit.foundation.ScreenProvider
import org.koin.androidx.compose.koinViewModel


@Composable
fun CreatePinCodeContent(state: RegistrationState, onEvent: (RegistrationEvent) -> Unit) {
	val colors = AppTheme.colors
	val typography = AppTheme.typography

	val shape = RoundedCornerShape(50)



	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(colors.white)
			.statusBarsPadding()
			.padding(top = 59.dp, start = 20.dp, end = 20.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		BasicText("Создайте пароль", style = typography.h1ExtraBold)
		BasicText(
			"Для защиты ваших персональных данных",
			style = typography.captionRegular,
			color = { colors.caption })

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
		Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 44.dp)) {
			val pinCodeNumbersList = (1..9).toList()
			LazyVerticalGrid(
				columns = GridCells.Fixed(3),
				state = gridState,
				verticalArrangement = Arrangement.spacedBy(24.dp),
				horizontalArrangement = Arrangement.spacedBy(24.dp),
			) {
				items(items = pinCodeNumbersList) { i ->
					val interactionSource = remember { MutableInteractionSource() }
					val isPressed by interactionSource.collectIsPressedAsState()
					Box(
						modifier = Modifier
							.aspectRatio(1f)
							.background(
								if (isPressed) colors.accent else colors.inputBackground,
								shape = shape
							)
							.clickable(interactionSource = interactionSource, indication = null) {
								onEvent(RegistrationEvent.AddSymbolPinCode(i.toString()))
							},
						contentAlignment = Alignment.Center
					) {
						BasicText(
							text = i.toString(),
							style = typography.h1ExtraBold,
							color = { if (isPressed) colors.white else colors.black })
					}
				}
				item { Spacer(modifier = Modifier.aspectRatio(1f)) }
				item {
					val interactionSource = remember { MutableInteractionSource() }
					val isPressed by interactionSource.collectIsPressedAsState()
					Box(
						modifier = Modifier
							.aspectRatio(1f)
							.background(
								if (isPressed) colors.accent else colors.inputBackground,
								shape = shape
							)
							.clickable(interactionSource = interactionSource, indication = null) {
								onEvent(RegistrationEvent.AddSymbolPinCode(0.toString()))
							},
						contentAlignment = Alignment.Center
					) {
						BasicText(
							text = 0.toString(),
							style = typography.h1ExtraBold,
							color = { if (isPressed) colors.white else colors.black })
					}
				}
			}
		}
	}
}


@Composable
fun CreatePinCodeScreen(viewModel: RegistrationViewModel = koinViewModel()) {

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