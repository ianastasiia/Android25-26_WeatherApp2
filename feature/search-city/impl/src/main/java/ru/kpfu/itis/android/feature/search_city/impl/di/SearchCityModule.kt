package ru.kpfu.itis.android.feature.search_city.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.kpfu.itis.android.feature.search_city.api.domain.repository.CitySearchRepository
import ru.kpfu.itis.android.feature.search_city.api.domain.usecase.SearchCityUseCase
import ru.kpfu.itis.android.feature.search_city.impl.data.remote.CitySearchApi
import ru.kpfu.itis.android.feature.search_city.impl.data.repository.CitySearchRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchCityModule {

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): CitySearchApi =
        retrofit.create(CitySearchApi::class.java)

    @Provides
    @Singleton
    fun provideCitySearchRepository(api: CitySearchApi): CitySearchRepository =
        CitySearchRepositoryImpl(api = api)

    @Provides
    fun provideSearchCityUseCase(repository: CitySearchRepository): SearchCityUseCase =
        SearchCityUseCase(repository = repository)

}