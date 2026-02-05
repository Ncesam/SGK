package com.ncesam.sgk2026

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ncesam.sgk2026.presentation.navigation.MainNavigator
import com.ncesam.uikit.foundation.AppThemeProvider
import com.ncesam.uikit.foundation.ScreenProvider

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		installSplashScreen()
		super.onCreate(savedInstanceState)
		enableEdgeToEdge(statusBarStyle = SystemBarStyle.light(10, 20))
		setContent {
			AppThemeProvider {
				ScreenProvider {
 					MainNavigator()
				}
			}
		}

	}
}



