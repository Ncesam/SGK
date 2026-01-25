package com.ncesam.sgk2026

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.lifecycleScope
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		actionBar?.hide()
		setContent {
			AppThemeProvider {
				SplashScreenContent()
			}
		}

		lifecycleScope.launch {
			delay(2000)
			val intent = Intent(this@SplashActivity, MainActivity::class.java)
			startActivity(intent)
			finish()
		}

	}
}


@Composable
fun SplashScreenContent() {
	val gradient = Brush.linearGradient(
		colors = listOf(
			Color("#003D8E".toColorInt()),
			Color("#A371FF".toColorInt())
		),
		start = Offset(0f, Float.POSITIVE_INFINITY),
		end = Offset(Float.POSITIVE_INFINITY, 0f)
	)

	val colors = AppTheme.colors
	val typography = AppTheme.typography
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(gradient),
		contentAlignment = Alignment.Center
	) {
		BasicText(
			text = "Travel",
			color = { colors.white },
			style = typography.h1ExtraBold.copy(fontSize = 64.sp)
		)
	}
}