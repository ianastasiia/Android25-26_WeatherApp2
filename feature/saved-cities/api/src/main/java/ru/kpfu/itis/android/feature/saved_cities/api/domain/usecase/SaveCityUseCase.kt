package ru.kpfu.itis.android.feature.saved_cities.api.domain.usecase

import ru.kpfu.itis.android.feature.saved_cities.api.domain.model.SavedCity
import ru.kpfu.itis.android.feature.saved_cities.api.domain.repository.SavedCityRepository

class SaveCityUseCase(
    private val repository: SavedCityRepository
) {
    suspend operator fun invoke(city: SavedCity) = repository.savedCity(city)
}