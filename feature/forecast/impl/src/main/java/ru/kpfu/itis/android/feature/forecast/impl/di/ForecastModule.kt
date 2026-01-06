package ru.kpfu.itis.android.feature.forecast.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.kpfu.itis.android.feature.forecast.api.domain.repository.ForecastRepository
import ru.kpfu.itis.android.feature.forecast.api.domain.usecase.GetForecastUseCase
import ru.kpfu.itis.android.feature.forecast.impl.data.remote.ForecastApi
import ru.kpfu.itis.android.feature.forecast.impl.data.repository.ForecastRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ForecastModule {

    @Provides
    @Singleton
    fun provideForecastApi(retrofit: Retrofit): ForecastApi =
        retrofit.create(ForecastApi::class.java)

    @Provides
    @Singleton
    fun provideForecastRepository(api: ForecastApi): ForecastRepository =
        ForecastRepositoryImpl(api)

    @Provides
    fun provideGetForecastUseCase(
        repository: ForecastRepository
    ): GetForecastUseCase =
        GetForecastUseCase(repository)
}