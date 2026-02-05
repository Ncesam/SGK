package com.ncesam.sgk2026.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ncesam.sgk2026.domain.states.ShopCartEvent
import com.ncesam.sgk2026.domain.states.ShopCartState
import com.ncesam.uikit.foundation.AppThemeProvider
import com.ncesam.uikit.foundation.ScreenProvider
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ShopCartContent(state: ShopCartState, onEvent: (ShopCartEvent) -> Unit) {

}

@Composable
fun ShopCartScreen(viewModel: ShopCartViewModel = koinViewModel()) {

}

@Preview
@Composable
fun PreviewShopCart() {
	AppThemeProvider {
		ScreenProvider {

		}
	}
}