package ru.kpfu.itis.android.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kpfu.itis.android.core.database.dao.CityDao
import ru.kpfu.itis.android.core.domain.usecase.SaveCityUseCase

@Module
@InstallIn(SingletonComponent::class)
object CityUseCaseModule {

    @Provides
    fun provideSaveCityUseCase(
        cityDao: CityDao
    ): SaveCityUseCase = SaveCityUseCase(cityDao)
}
