package ru.kpfu.itis.android.feature.saved_cities.api.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kpfu.itis.android.feature.saved_cities.api.domain.model.SavedCity

interface SavedCityRepository {
    fun observeSavedCities(): Flow<List<SavedCity>>
    suspend fun savedCity(city: SavedCity)
    suspend fun deleteCity(cityName: String)

}