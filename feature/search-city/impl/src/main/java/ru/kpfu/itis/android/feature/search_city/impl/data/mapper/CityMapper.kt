package ru.kpfu.itis.android.feature.search_city.impl.data.mapper

import ru.kpfu.itis.android.feature.search_city.api.domain.model.City
import ru.kpfu.itis.android.feature.search_city.impl.data.remote.CityDto

object CityMapper {

    fun map(dto: CityDto): City = City(
        name = dto.name, country = dto.country, query = "${dto.latitude},${dto.longitude}"
    )

}