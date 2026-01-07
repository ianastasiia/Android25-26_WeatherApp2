package ru.kpfu.itis.android.feature.forecast.api.domain.usecase

import ru.kpfu.itis.android.feature.forecast.api.domain.repository.ForecastRepository

class GetForecastUseCase(
    private val repository: ForecastRepository
) {
    suspend operator fun invoke(query: String, days: Int = 10) =
        repository.getForecast(query = query, days = days)
}