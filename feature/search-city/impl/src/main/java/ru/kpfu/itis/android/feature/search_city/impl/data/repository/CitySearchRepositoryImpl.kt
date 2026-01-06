package ru.kpfu.itis.android.feature.search_city.impl.data.repository

import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.core.network.util.safeApiCall
import ru.kpfu.itis.android.feature.search_city.api.domain.model.City
import ru.kpfu.itis.android.feature.search_city.api.domain.repository.CitySearchRepository
import ru.kpfu.itis.android.feature.search_city.impl.data.mapper.CityMapper
import ru.kpfu.itis.android.feature.search_city.impl.data.remote.CitySearchApi

class CitySearchRepositoryImpl(
    private val api: CitySearchApi
) : CitySearchRepository {

    override suspend fun search(query: String): ApiResult<List<City>> =
        safeApiCall {
            api.search(query = query).map(CityMapper::map)
        }

}