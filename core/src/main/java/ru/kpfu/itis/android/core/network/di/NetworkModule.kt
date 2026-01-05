package ru.kpfu.itis.android.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.kpfu.itis.android.core.BuildConfig
import ru.kpfu.itis.android.core.network.retrofit.OkHttpProvider
import ru.kpfu.itis.android.core.network.retrofit.RetrofitProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpProvider.provide(
        apiKey = BuildConfig.WEATHER_API_KEY,
    )

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = RetrofitProvider.provide(
        baseUrl = BuildConfig.BASE_URL,
        client = client,
    )

}