package ru.kpfu.itis.android.feature.current_weather.api.domain.usecase

import ru.kpfu.itis.android.feature.current_weather.api.domain.repository.CurrentWeatherRepository

class GetCurrentWeatherUseCase(
    private val repository: CurrentWeatherRepository
) {

    suspend operator fun invoke(query: String) = repository.getCurrentWeather(query = query)

}