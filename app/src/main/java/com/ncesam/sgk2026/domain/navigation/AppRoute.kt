package com.ncesam.sgk2026.domain.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {
	val routeId: String


	// Splash
	@Serializable
	object Splash : AppRoute {
		override val routeId: String
			get() = "splash"
	}

	@Serializable
	data class Search(val value: String) : AppRoute {
		override val routeId: String
			get() = "search"
	}

	// Graphs
	@Serializable
	object MainGraph : AppRoute {
		override val routeId: String
			get() = "main_graph"
	}


	@Serializable
	object AuthGraph : AppRoute {
		override val routeId: String
			get() = "auth_graph"
	}

	// Auth Routes
	@Serializable
	object Login : AppRoute {
		override val routeId: String
			get() = "login"

	}

	@Serializable
	object CreateProfile : AppRoute {
		override val routeId: String
			get() = "create_profile"
	}

	@Serializable
	object CreatePassword : AppRoute {
		override val routeId = "create_password"
	}

	@Serializable
	object CreatePinCode : AppRoute {
		override val routeId = "create_pincode"
	}


	@Serializable
	object Main : AppRoute {
		override val routeId = "main"
	}


	@Serializable
	object Profile : AppRoute {
		override val routeId = "profile"
	}

	@Serializable
	object ShopCart : AppRoute {
		override val routeId = "shop_cart"
	}

	@Serializable
	data class Booking(
		val hotelId: String
	) : AppRoute {
		override val routeId = "booking"
	}

	@Serializable
	object MyBooking : AppRoute {
		override val routeId: String
			get() = "my_booking"
	}
}
