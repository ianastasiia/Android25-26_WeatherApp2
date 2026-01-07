package ru.kpfu.itis.android.feature.current_weather.api.domain.model

data class CurrentWeather(
    val cityName: String,
    val temperatureInCelsius: Int,
    val description: String,
    val latitude: Double,
    val longitude: Double,
)
