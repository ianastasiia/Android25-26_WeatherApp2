package ru.kpfu.itis.android.feature.saved_cities.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kpfu.itis.android.core.database.dao.CityDao
import ru.kpfu.itis.android.feature.saved_cities.api.domain.repository.SavedCityRepository
import ru.kpfu.itis.android.feature.saved_cities.api.domain.usecase.DeleteCityUseCase
import ru.kpfu.itis.android.feature.saved_cities.api.domain.usecase.GetSavedCitiesUseCase
import ru.kpfu.itis.android.feature.saved_cities.api.domain.usecase.SaveCityUseCase
import ru.kpfu.itis.android.feature.saved_cities.impl.data.repository.SavedCityRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SavedCitiesModule {
    @Provides
    @Singleton
    fun provideRepository(dao: CityDao): SavedCityRepository = SavedCityRepositoryImpl(dao)

    @Provides
    fun provideObserveCitiesUseCase(repository: SavedCityRepository) =
        GetSavedCitiesUseCase(repository = repository)

    @Provides
    fun provideSaveCityUseCase(repository: SavedCityRepository) =
        SaveCityUseCase(repository = repository)

    @Provides
    fun provideDeleteCityUseCase(repository: SavedCityRepository) =
        DeleteCityUseCase(repository = repository)

}