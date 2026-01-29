package com.ncesam.uikit.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncesam.uikit.R
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider


@Composable()
fun AppNavigationBottomTab(
	item: AppNavigationBottomTabItem,
	selected: Boolean = false,
	onClick: () -> Unit
) {
	val colors = AppTheme.colors
	val typography = AppTheme.typography
	Column(
		modifier = Modifier.clickable { onClick() },
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Icon(
			painter = painterResource(item.icon),
			contentDescription = null,
			modifier = Modifier.size(32.dp),
			tint = if (selected) colors.accent else colors.icons
		)
		BasicText(
			text = item.title,
			style = typography.captionRegular,
			color = { if (selected) colors.accent else colors.icons }
		)
	}
}

enum class AppNavigationBottomTabItem(@DrawableRes val icon: Int, val title: String) {
	Home(icon = R.drawable.home, "Главная"),
	Search(icon = R.drawable.search, "Поиск"),
	Travels(icon = R.drawable.calendar, "Поездки"),
	Profile(icon = R.drawable.profile, "Профиль"),
}

@Preview
@Composable
fun PreviewAppNavigationBottomTabs() {
	AppThemeProvider {
		Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
			AppNavigationBottomTab(AppNavigationBottomTabItem.Home, false) {}
			AppNavigationBottomTab(AppNavigationBottomTabItem.Search, false) {}
			AppNavigationBottomTab(AppNavigationBottomTabItem.Travels, false) {}
			AppNavigationBottomTab(AppNavigationBottomTabItem.Profile, false) {}
		}

	}
}