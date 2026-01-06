package ru.kpfu.itis.android.feature.current_weather.impl.data.repository

import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.core.network.util.safeApiCall
import ru.kpfu.itis.android.feature.current_weather.api.domain.model.CurrentWeather
import ru.kpfu.itis.android.feature.current_weather.api.domain.repository.CurrentWeatherRepository
import ru.kpfu.itis.android.feature.current_weather.impl.data.mapper.CurrentWeatherMapper
import ru.kpfu.itis.android.feature.current_weather.impl.data.remote.CurrentWeatherApi

class CurrentWeatherRepositoryImpl(
    private val api: CurrentWeatherApi
) : CurrentWeatherRepository {

    override suspend fun getCurrentWeather(query: String): ApiResult<CurrentWeather> =
        safeApiCall {
            CurrentWeatherMapper.map(
                api.getCurrentWeather(query)
            )
        }
}