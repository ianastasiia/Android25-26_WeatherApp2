package ru.kpfu.itis.android.feature.saved_cities.impl.data.mapper

import ru.kpfu.itis.android.core.database.entity.CityEntity
import ru.kpfu.itis.android.feature.saved_cities.api.domain.model.SavedCity

object SavedCityMapper {
    fun mapFromEntity(cityEntity: CityEntity): SavedCity =
        SavedCity(
            name = cityEntity.name,
            latitude = cityEntity.latitude,
            longitude = cityEntity.longitude
        )

    fun mapToEntity(city: SavedCity): CityEntity =
        CityEntity(
            name = city.name,
            latitude = city.latitude,
            longitude = city.longitude
        )

}