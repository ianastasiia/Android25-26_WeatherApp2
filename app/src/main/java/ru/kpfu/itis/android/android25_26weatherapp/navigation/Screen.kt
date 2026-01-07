package ru.kpfu.itis.android.android25_26weatherapp.navigation

sealed class Screen(val route: String) {

    data object CurrentWeather : Screen("current_weather/{city}") {
        fun createRoute(city: String) = "current_weather/$city"
        const val ARG_CITY = "city"
    }

    data object SearchCity : Screen("search_city")

    data object SavedCities : Screen("saved_cities")

    data object Forecast : Screen("forecast/{city}") {
        fun createRoute(city: String) = "forecast/$city"
        const val ARG_CITY = "city"
    }
}
