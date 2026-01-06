package ru.kpfu.itis.android.feature.forecast.impl.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    val forecast: ForecastDaysDto
)

@Serializable
data class ForecastDaysDto(
    @SerialName("forecastday")
    val days: List<ForecastDayDto>
)

@Serializable
data class ForecastDayDto(
    val date: String,
    val day: DayDto
)

@Serializable
data class DayDto(
    @SerialName("avgtemp_c")
    val avgTempC: Double,
    val condition: ConditionDto
)

@Serializable
data class ConditionDto(
    val text: String,
    val icon: String
)