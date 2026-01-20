package com.ncesam.uikit.foundation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt


data class AppTypographySchema(
    val h1SemiBold: TextStyle,
    val h1ExtraBold: TextStyle,
    val h2Regular: TextStyle,
    val h2SemiBold: TextStyle,
    val h2ExtraBold: TextStyle,
    val h3Regular: TextStyle,
    val h3Medium: TextStyle,
    val h3SemiBold: TextStyle,
    val headlineRegular: TextStyle,
    val headlineMedium: TextStyle,
    val textRegular: TextStyle,
    val textMedium: TextStyle,
    val captionRegular: TextStyle,
    val captionSemiBold: TextStyle,
    val caption2Regular: TextStyle,
    val caption2Bold: TextStyle,
)

val appTypography = AppTypographySchema(
    h1SemiBold = TextStyle(
        fontWeight = FontWeight(600),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        color = Color("#000000".toColorInt())
    ), h1ExtraBold = TextStyle(
        fontWeight = FontWeight(800),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        color = Color("#000000".toColorInt())
    ), h2Regular = TextStyle(
        fontWeight = FontWeight(400),
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), h2SemiBold = TextStyle(
        fontWeight = FontWeight(600),
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), h2ExtraBold = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), h3Medium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), h3Regular = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), h3SemiBold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), headlineMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), headlineRegular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), textMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), textRegular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), captionRegular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), captionSemiBold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), caption2Bold = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    ), caption2Regular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = RobotoFontFamily,
        color = Color("#000000".toColorInt())
    )
)