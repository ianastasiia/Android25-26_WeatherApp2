package ru.kpfu.itis.android.feature.search_city.api.domain.usecase

import ru.kpfu.itis.android.feature.search_city.api.domain.repository.CitySearchRepository

class SearchCityUseCase(
    private val repository: CitySearchRepository
) {

    suspend operator fun invoke(query: String) = repository.search(query = query)

}