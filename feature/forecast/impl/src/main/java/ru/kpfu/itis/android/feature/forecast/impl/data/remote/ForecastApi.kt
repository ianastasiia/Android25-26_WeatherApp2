package ru.kpfu.itis.android.feature.forecast.impl.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") query: String,
        @Query("days") days: Int
    ): ForecastDto
}