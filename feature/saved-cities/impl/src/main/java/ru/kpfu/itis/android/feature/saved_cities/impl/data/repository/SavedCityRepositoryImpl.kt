package ru.kpfu.itis.android.feature.saved_cities.impl.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.kpfu.itis.android.core.database.dao.CityDao
import ru.kpfu.itis.android.feature.saved_cities.api.domain.model.SavedCity
import ru.kpfu.itis.android.feature.saved_cities.api.domain.repository.SavedCityRepository
import ru.kpfu.itis.android.feature.saved_cities.impl.data.mapper.SavedCityMapper

class SavedCityRepositoryImpl(
    private val dao: CityDao
) : SavedCityRepository {
    override fun observeSavedCities(): Flow<List<SavedCity>> =
        dao.observeCities().map { list ->
            list.map(SavedCityMapper::mapFromEntity)

        }

    override suspend fun savedCity(city: SavedCity) {
        dao.insertCity(SavedCityMapper.mapToEntity(city = city))
    }

    override suspend fun deleteCity(cityName: String) {
        dao.deleteCity(cityName = cityName)
    }

}