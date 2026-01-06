package ru.kpfu.itis.android.feature.current_weather.api.domain.repository

import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.feature.current_weather.api.domain.model.CurrentWeather

interface CurrentWeatherRepository {

    suspend fun getCurrentWeather(
        query: String
    ): ApiResult<CurrentWeather>
}