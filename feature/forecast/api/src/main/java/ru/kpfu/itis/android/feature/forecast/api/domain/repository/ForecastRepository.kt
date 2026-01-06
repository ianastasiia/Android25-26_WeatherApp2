package ru.kpfu.itis.android.feature.forecast.api.domain.repository

import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.feature.forecast.api.domain.model.ForecastDay

interface ForecastRepository {

    suspend fun getForecast(
        query: String,
        days: Int
    ): ApiResult<List<ForecastDay>>
}