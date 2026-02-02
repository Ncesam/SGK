package com.ncesam.sgk2026.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ncesam.sgk2026.domain.models.Facility
import com.ncesam.sgk2026.domain.models.Hotel
import com.ncesam.sgk2026.domain.states.MainEvent
import com.ncesam.sgk2026.domain.states.MainState
import com.ncesam.sgk2026.presentation.viewModels.MainViewModel
import com.ncesam.uikit.components.AppBottomSheet
import com.ncesam.uikit.components.AppButton
import com.ncesam.uikit.components.AppButtonSize
import com.ncesam.uikit.components.AppButtonStyle
import com.ncesam.uikit.components.AppInputSearch
import com.ncesam.uikit.components.PrimaryAppCard
import com.ncesam.uikit.components.SmallAppCard
import com.ncesam.uikit.foundation.AppTheme
import com.ncesam.uikit.foundation.AppThemeProvider
import com.ncesam.uikit.foundation.ScreenProvider
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainContent(state: MainState, onEvent: (MainEvent) -> Unit) {
	var searchFocused by remember { mutableStateOf(false) }
	var isSheetVisible by remember {
		mutableStateOf(false)
	}

	val colors = AppTheme.colors
	val typography = AppTheme.typography


	LaunchedEffect(state.selectedHotel) {
		isSheetVisible = state.selectedHotel != null
	}
	Column(
		modifier = Modifier
			.fillMaxSize()
			.imePadding()
			.statusBarsPadding()
			.background(colors.white)
	) {
		LazyColumn(
			modifier = Modifier
				.fillMaxSize()
				.padding(20.dp),
			verticalArrangement = Arrangement.spacedBy(44.dp)
		) {
			item {
				Row(
					horizontalArrangement = Arrangement.spacedBy(30.dp),
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.fillMaxWidth()
				) {
					AppInputSearch(
						{ focusState -> searchFocused = focusState.isFocused },
						{ text -> onEvent(MainEvent.SearchChanged(text)) },
						placeholder = "Искать описание",
						value = state.search,
						isFocused = searchFocused,
						modifier = Modifier.weight(1f)
					)
					Icon(
						painter = AppTheme.icons.Profile,
						tint = colors.icons,
						contentDescription = null,
						modifier = Modifier.size(32.dp)
					)
				}
			}
			item {
				if (state.salesHotels.isNotEmpty()) {
					Column(
						verticalArrangement = Arrangement.spacedBy(3.dp),
						horizontalAlignment = Alignment.Start
					) {
						BasicText(
							"Акции и новости",
							style = typography.h3SemiBold,
							color = { colors.caption })
						LazyRow(
							horizontalArrangement = Arrangement.spacedBy(8.dp)
						) {
							items(state.salesHotels) { hotel ->
								SmallAppCard { onEvent(MainEvent.ViewHotel(hotel.id)) }
							}
						}
					}
				}
			}

			item {
				Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
					if (state.categories.isNotEmpty()) {
						Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
							BasicText(
								"Каталог описаний",
								style = typography.h3SemiBold,
								color = { colors.caption })
							LazyRow(
								horizontalArrangement = Arrangement.spacedBy(16.dp),
							) {
								items(state.categories) { category ->
									AppButton(
										style = if (state.selectedCategory == category) AppButtonStyle.Accent else AppButtonStyle.Default,
										size = AppButtonSize.Chip,
										content = category,
										onClick = {
											onEvent(
												MainEvent.SelectedCategoryChanged(
													category
												)
											)
										})
								}
							}
						}
					}
					Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
						state.allHotels.map { hotel ->
							PrimaryAppCard(
								{ onEvent(MainEvent.ViewHotel(hotel.id)) },
								{ onEvent(MainEvent.ViewHotel(hotel.id)) },
								price = hotel.cost.toInt()
							)
						}
					}
				}
			}

		}
		state.selectedHotel?.let { hotel ->
			AppBottomSheet(
				{ isSheetVisible = false },
				isVisible = isSheetVisible,
				name = hotel.title
			) {
				Column(
					verticalArrangement = Arrangement.spacedBy(80.dp)
				) {
					Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
						BasicText(
							text = "Описание", style = typography.headlineMedium,
							color = { colors.caption })
						BasicText(text = hotel.description, style = typography.textRegular)
					}
					Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
						Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
							BasicText(
								text = "Удобства", style = typography.headlineMedium,
								color = { colors.caption })
							Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
								hotel.facilities.map { facility ->
									BasicText(facility.value, style = typography.headlineMedium)
								}
							}
						}
						AppButton(
							style = AppButtonStyle.Accent,
							content = "Добавить за ${hotel.cost}₽",
							onClick = { onEvent(MainEvent.AddToCart(hotel.id)) })
					}

				}
			}
		}

	}
}


@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {

}


@Preview
@Composable
fun PreviewMain() {
	var state by remember {
		mutableStateOf(
			MainState(
				categories = listOf("Популярное", "OutDoor", "BasketBall"),
				selectedCategory = "Популярное",
				allHotels = listOf(
					Hotel(
						"54534534",
						"Отель гранд-будапешт",
						"""В самом сердце Стокгольма, где встречаются королевская набережная и дыхание Балтики, расположился Grand Waterfront Residence — легендарный символ шведской элегантности уже более 150 лет.
Панорамные окна номеров открывают завораживающий вид на Королевский дворец, Старый город Гамла Стан и мерцающую гладь залива. Мраморные холлы, хрустальные люстры, сдержанная роскошь скандинавского шика и легендарный сервис, которому доверяют нобелевские лауреаты и мировые знаменитости.""",
						"654654",
						"400",
						listOf(Facility.Spa, Facility.WiFi)
					),
					Hotel(
						"54534534",
						"Отель гранд-будапешт",
						"""В самом сердце Стокгольма, где встречаются королевская набережная и дыхание Балтики, расположился Grand Waterfront Residence — легендарный символ шведской элегантности уже более 150 лет.
Панорамные окна номеров открывают завораживающий вид на Королевский дворец, Старый город Гамла Стан и мерцающую гладь залива. Мраморные холлы, хрустальные люстры, сдержанная роскошь скандинавского шика и легендарный сервис, которому доверяют нобелевские лауреаты и мировые знаменитости.""",
						"654654",
						"400",
						listOf(Facility.Spa, Facility.WiFi)
					),
					Hotel(
						"645645",
						"Отель гранд-будапешт",
						"""В самом сердце Стокгольма, где встречаются королевская набережная и дыхание Балтики, расположился Grand Waterfront Residence — легендарный символ шведской элегантности уже более 150 лет.
Панорамные окна номеров открывают завораживающий вид на Королевский дворец, Старый город Гамла Стан и мерцающую гладь залива. Мраморные холлы, хрустальные люстры, сдержанная роскошь скандинавского шика и легендарный сервис, которому доверяют нобелевские лауреаты и мировые знаменитости.""",
						"654654",
						"400",
						listOf(Facility.Spa, Facility.WiFi)
					),
					Hotel(
						"654654",
						"Отель гранд-будапешт",
						"""В самом сердце Стокгольма, где встречаются королевская набережная и дыхание Балтики, расположился Grand Waterfront Residence — легендарный символ шведской элегантности уже более 150 лет.
Панорамные окна номеров открывают завораживающий вид на Королевский дворец, Старый город Гамла Стан и мерцающую гладь залива. Мраморные холлы, хрустальные люстры, сдержанная роскошь скандинавского шика и легендарный сервис, которому доверяют нобелевские лауреаты и мировые знаменитости.""",
						"654654",
						"400",
						listOf(Facility.Spa, Facility.WiFi)
					),
					Hotel(
						"756756",
						"Отель гранд-будапешт",
						"""В самом сердце Стокгольма, где встречаются королевская набережная и дыхание Балтики, расположился Grand Waterfront Residence — легендарный символ шведской элегантности уже более 150 лет.
Панорамные окна номеров открывают завораживающий вид на Королевский дворец, Старый город Гамла Стан и мерцающую гладь залива. Мраморные холлы, хрустальные люстры, сдержанная роскошь скандинавского шика и легендарный сервис, которому доверяют нобелевские лауреаты и мировые знаменитости.""",
						"654654",
						"400",
						listOf(Facility.Spa, Facility.WiFi)
					)
				),
				salesHotels = listOf(
					Hotel(
						"756765",
						"Отель гранд-будапешт",
						"""В самом сердце Стокгольма, где встречаются королевская набережная и дыхание Балтики, расположился Grand Waterfront Residence — легендарный символ шведской элегантности уже более 150 лет.
Панорамные окна номеров открывают завораживающий вид на Королевский дворец, Старый город Гамла Стан и мерцающую гладь залива. Мраморные холлы, хрустальные люстры, сдержанная роскошь скандинавского шика и легендарный сервис, которому доверяют нобелевские лауреаты и мировые знаменитости.""",
						"654654",
						"400",
						listOf(Facility.Spa, Facility.WiFi)
					),
					Hotel(
						"543543",
						"Отель гранд-будапешт",
						"""В самом сердце Стокгольма, где встречаются королевская набережная и дыхание Балтики, расположился Grand Waterfront Residence — легендарный символ шведской элегантности уже более 150 лет.
Панорамные окна номеров открывают завораживающий вид на Королевский дворец, Старый город Гамла Стан и мерцающую гладь залива. Мраморные холлы, хрустальные люстры, сдержанная роскошь скандинавского шика и легендарный сервис, которому доверяют нобелевские лауреаты и мировые знаменитости.""",
						"654654",
						"400",
						listOf(Facility.Spa, Facility.WiFi)
					)
				),
			)
		)
	}
	AppThemeProvider {
		ScreenProvider {
			MainContent(state) { event ->
				when (event) {
					is MainEvent.ViewHotel -> {
						state =
							state.copy(selectedHotel = state.allHotels.find { hotel -> hotel.id == event.id })
					}

					else -> {}
				}
			}
		}
	}
}