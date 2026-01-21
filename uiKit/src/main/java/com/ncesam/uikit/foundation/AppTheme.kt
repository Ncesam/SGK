package com.ncesam.uikit.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.ncesam.uikit.icons.AppIcons

val LocalAppColors = staticCompositionLocalOf<AppColorScheme> {
    error("No AppColorScheme provided! Wrap your app in AppTheme.")
}

val LocalAppTypography = staticCompositionLocalOf<AppTypographySchema> {
    error("No AppTypography provided! Wrap your app in AppTheme.")
}


@Composable
fun AppThemeProvider(
    content: @Composable () -> Unit
) {
    val typography = remember { appTypography }
    val colors = remember { LightColorPalette }

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography
    ) {
        content()
    }
}


object AppTheme {
    val colors: AppColorScheme
        @Composable
        get() = LocalAppColors.current
    val typography: AppTypographySchema
        @Composable
        get() = LocalAppTypography.current
    val icons = AppIcons
}