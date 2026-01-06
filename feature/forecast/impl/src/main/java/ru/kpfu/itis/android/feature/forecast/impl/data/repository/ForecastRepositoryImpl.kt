package ru.kpfu.itis.android.feature.forecast.impl.data.repository

import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.core.network.util.safeApiCall
import ru.kpfu.itis.android.feature.forecast.api.domain.model.ForecastDay
import ru.kpfu.itis.android.feature.forecast.api.domain.repository.ForecastRepository
import ru.kpfu.itis.android.feature.forecast.impl.data.mapper.ForecastMapper
import ru.kpfu.itis.android.feature.forecast.impl.data.remote.ForecastApi

class ForecastRepositoryImpl(
    private val api: ForecastApi
) : ForecastRepository {

    override suspend fun getForecast(
        query: String,
        days: Int
    ): ApiResult<List<ForecastDay>> =
        safeApiCall {
            api.getForecast(query, days)
                .forecast.days
                .map(ForecastMapper::map)
        }
}