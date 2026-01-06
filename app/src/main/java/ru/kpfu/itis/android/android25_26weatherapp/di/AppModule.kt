package ru.kpfu.itis.android.android25_26weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideFirebaseAnalytics(): FirebaseAnalytics = Firebase.analytics
//
//    @Provides
//    @Singleton
//    fun provideFirebaseCrashlytics(): FirebaseCrashlytics =
//        FirebaseCrashlytics.getInstance()
//
//    @Provides
//    @Singleton
//    fun provideFirebasePerformance(): FirebasePerformance =
//        FirebasePerformance.getInstance()
}