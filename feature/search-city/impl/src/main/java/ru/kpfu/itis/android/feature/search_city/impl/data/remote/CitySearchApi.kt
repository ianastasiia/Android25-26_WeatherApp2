package ru.kpfu.itis.android.feature.search_city.impl.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CitySearchApi {

    @GET("search.json")
    suspend fun search(
        @Query("q") query: String
    ): List<CitySearchDto>
}