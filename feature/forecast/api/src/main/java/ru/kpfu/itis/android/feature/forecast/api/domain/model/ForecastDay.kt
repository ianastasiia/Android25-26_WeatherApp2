package ru.kpfu.itis.android.feature.forecast.api.domain.model

data class ForecastDay(
    val date: String,
    val avgTempC: Int,
    val condition: String,
    val iconUrl: String
)
