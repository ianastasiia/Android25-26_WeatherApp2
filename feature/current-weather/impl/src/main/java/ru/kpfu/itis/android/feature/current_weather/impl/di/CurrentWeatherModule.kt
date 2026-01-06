package ru.kpfu.itis.android.feature.current_weather.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.kpfu.itis.android.feature.current_weather.api.domain.repository.CurrentWeatherRepository
import ru.kpfu.itis.android.feature.current_weather.api.domain.usecase.GetCurrentWeatherUseCase
import ru.kpfu.itis.android.feature.current_weather.impl.data.remote.CurrentWeatherApi
import ru.kpfu.itis.android.feature.current_weather.impl.data.repository.CurrentWeatherRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrentWeatherModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherApi(retrofit: Retrofit): CurrentWeatherApi = retrofit.create(
        CurrentWeatherApi::class.java
    )

    @Provides
    @Singleton
    fun provideCurrentWeatherRepository(api: CurrentWeatherApi): CurrentWeatherRepository =
        CurrentWeatherRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideCurrentWeatherUseCase(repository: CurrentWeatherRepository): GetCurrentWeatherUseCase =
        GetCurrentWeatherUseCase(repository)
}