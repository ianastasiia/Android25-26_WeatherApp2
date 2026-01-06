package ru.kpfu.itis.android.feature.current_weather.impl.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    val location: LocationDto,
    val current: CurrentDto,
)

@Serializable
data class LocationDto(
    val name: String,
    val country: String,
    val lan: Double,
    val lon: Double,
)

@Serializable
data class CurrentDto(
    @SerialName("temp_c")
    val tempC: Double,
    val condition: ConditionDto
)

@Serializable
data class ConditionDto(
    val text: String,
    val icon: String
)