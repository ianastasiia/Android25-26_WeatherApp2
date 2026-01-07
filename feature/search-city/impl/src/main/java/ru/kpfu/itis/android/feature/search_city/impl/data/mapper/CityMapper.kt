package ru.kpfu.itis.android.feature.search_city.impl.data.mapper

import ru.kpfu.itis.android.feature.search_city.api.domain.model.City
import ru.kpfu.itis.android.feature.search_city.impl.data.remote.CitySearchResponseDto

object CityMapper {

    fun map(dto: CitySearchResponseDto): City = City(
        name = dto.name,
        country = dto.country,
        query = "${dto.lat},${dto.lon}"
    )

}