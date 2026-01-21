package com.ncesam.uikit.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.ncesam.uikit.R


object AppIcons {
    val ArrowDown: Painter
        @Composable get() = painterResource(id = R.drawable.arrow_down)
    val ArrowLeft: Painter
        @Composable get() = painterResource(id = R.drawable.arrow_left)
    val Chat: Painter
        @Composable get() = painterResource(id = R.drawable.chat)
    val Check: Painter
        @Composable get() = painterResource(R.drawable.check)
    val ClosedEye: Painter
        @Composable get() = painterResource(R.drawable.closed_eye)
    val Cross: Painter
        @Composable get() = painterResource(R.drawable.cross)
    val CrossRounded: Painter
        @Composable get() = painterResource(R.drawable.cross_rounded)
    val Download: Painter
        @Composable get() = painterResource(R.drawable.download)
    val File: Painter
        @Composable get() = painterResource(R.drawable.file)
    val Filter: Painter
        @Composable get() = painterResource(R.drawable.filter)
    val Map: Painter
        @Composable get() = painterResource(R.drawable.map)
    val Mic: Painter
        @Composable get() = painterResource(R.drawable.mic)
    val Minus: Painter
        @Composable get() = painterResource(R.drawable.minus)
    val More: Painter
        @Composable get() = painterResource(R.drawable.more)
    val OpenEye: Painter
        @Composable get() = painterResource(R.drawable.open_eye)
    val Paperclip: Painter
        @Composable get() = painterResource(R.drawable.paperclip)
    val Plus: Painter
        @Composable get() = painterResource(R.drawable.plus)
    val Search: Painter
        @Composable get() = painterResource(R.drawable.search)
    val Send: Painter
        @Composable get() = painterResource(R.drawable.send)
    val ShopCart: Painter
        @Composable get() = painterResource(R.drawable.shopcart)
    val Trash: Painter
        @Composable get() = painterResource(R.drawable.trash)
}