package ru.kpfu.itis.android.feature.current_weather.impl.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") query: String
    ): CurrentWeatherDto
}