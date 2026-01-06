package ru.kpfu.itis.android.feature.search_city.api.domain.repository

import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.feature.search_city.api.domain.model.City

interface CitySearchRepository {

    suspend fun search(query: String): ApiResult<List<City>>

}