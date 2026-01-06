package ru.kpfu.itis.android.feature.saved_cities.api.domain.usecase

import ru.kpfu.itis.android.feature.saved_cities.api.domain.repository.SavedCityRepository

class DeleteCityUseCase(
    private val repository: SavedCityRepository
) {
    suspend operator fun invoke(cityName: String) = repository.deleteCity(cityName = cityName)
}