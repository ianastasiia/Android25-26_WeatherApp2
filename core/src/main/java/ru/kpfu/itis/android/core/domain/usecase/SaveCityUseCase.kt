package ru.kpfu.itis.android.core.domain.usecase

import ru.kpfu.itis.android.core.database.dao.CityDao
import ru.kpfu.itis.android.core.database.entity.CityEntity

class SaveCityUseCase(
    private val cityDao: CityDao
) {

    suspend operator fun invoke(city: CityEntity) {
        cityDao.insertCity(city)
    }
}
