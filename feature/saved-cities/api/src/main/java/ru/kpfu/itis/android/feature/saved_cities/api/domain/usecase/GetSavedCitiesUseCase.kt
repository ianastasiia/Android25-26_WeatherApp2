package ru.kpfu.itis.android.feature.saved_cities.api.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.kpfu.itis.android.feature.saved_cities.api.domain.model.SavedCity
import ru.kpfu.itis.android.feature.saved_cities.api.domain.repository.SavedCityRepository

class GetSavedCitiesUseCase(
    private val repository: SavedCityRepository
) {
    operator fun invoke(): Flow<List<SavedCity>> = repository.observeSavedCities()
}