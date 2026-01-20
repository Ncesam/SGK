package com.ncesam.uikit.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppColors = staticCompositionLocalOf<AppColorScheme> {
    error("No AppColorScheme provided! Wrap your app in AppTheme.")
}

val LocalAppTypography = staticCompositionLocalOf<AppTypographySchema> {
    error("No AppTypography provided! Wrap your app in AppTheme.")
}

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colors = LightColorPalette

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides appTypography,
        content()
    )
}

object AppTheme {
    val colors: AppColorScheme
        @Composable
        get() = LocalAppColors.current
    val typography: AppTypographySchema
        @Composable
        get() = LocalAppTypography.current
}