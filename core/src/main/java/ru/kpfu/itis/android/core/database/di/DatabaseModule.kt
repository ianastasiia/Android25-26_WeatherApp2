package ru.kpfu.itis.android.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kpfu.itis.android.core.database.AppDatabase
import ru.kpfu.itis.android.core.database.dao.CityDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "weather_app.db"
        ).build()

    @Provides
    fun provideCityDao(
        database: AppDatabase,
    ): CityDao = database.cityDao()

}