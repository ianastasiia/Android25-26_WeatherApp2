package ru.kpfu.itis.android.android25_26weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.kpfu.itis.android.feature.current_weather.impl.presentation.screen.CurrentWeatherScreen
import ru.kpfu.itis.android.feature.forecast.impl.presentation.screen.ForecastScreen
import ru.kpfu.itis.android.feature.saved_cities.impl.presentation.screen.SavedCitiesScreen
import ru.kpfu.itis.android.feature.search_city.impl.presentation.screen.SearchCityScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SearchCity.route
    ) {

        composable(Screen.SearchCity.route) {
            SearchCityScreen(
                onCitySelected = { city ->
                    navController.navigate(
                        Screen.CurrentWeather.createRoute(city.name)
                    )
                }
            )
        }

        composable(
            route = Screen.CurrentWeather.route,
            arguments = listOf(
                navArgument(Screen.CurrentWeather.ARG_CITY) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val city =
                backStackEntry.arguments?.getString(Screen.CurrentWeather.ARG_CITY)!!

            CurrentWeatherScreen(
                city = city,
                onForecastClick = {
                    navController.navigate(
                        Screen.Forecast.createRoute(city)
                    )
                }
            )
        }

        composable(Screen.SavedCities.route) {
            SavedCitiesScreen(
                onCityClick = { cityName ->
                    navController.popBackStack()
                    navController.navigate(
                        Screen.CurrentWeather.createRoute(cityName)
                    )
                }
            )
        }

        composable(
            route = Screen.Forecast.route,
            arguments = listOf(
                navArgument(Screen.Forecast.ARG_CITY) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val city =
                backStackEntry.arguments?.getString(Screen.Forecast.ARG_CITY)!!

            ForecastScreen(city = city)
        }
    }
}
