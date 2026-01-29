package com.ncesam.uikit.foundation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ncesam.uikit.components.AppSnackBar
import kotlinx.coroutines.launch

val LocalAppSnackBarProvider = staticCompositionLocalOf<(String) -> Unit> {
	error("No AppSnackBar provided! Wrap your app in ScreenProvider.")
}


@Composable
fun ScreenProvider(content: @Composable () -> Unit) {
	val snackBarHostState = remember { SnackbarHostState() }
	val scope = rememberCoroutineScope()

	val showSnackBar: (String) -> Unit = { message ->
		scope.launch { snackBarHostState.showSnackbar(message = message) }
	}
	CompositionLocalProvider(
		LocalAppSnackBarProvider provides showSnackBar
	) {
		Box(modifier = Modifier.fillMaxSize()) {
			content()
			SnackbarHost(
				hostState = snackBarHostState,
				modifier = Modifier
					.align(Alignment.TopCenter)
					.padding(top = 16.dp)
			) { data ->
				AppSnackBar(data.visuals.message) { data.dismiss() }
			}

		}
	}
}

object ScreenContext {
	val showSnackBar: (String) -> Unit
		@Composable
		@ReadOnlyComposable
		get() = LocalAppSnackBarProvider.current
}