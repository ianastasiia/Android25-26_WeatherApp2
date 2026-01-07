package ru.kpfu.itis.android.android25_26weatherapp.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kpfu.itis.android.android25_26weatherapp.analytics.FirebaseAnalyticsTracker
import ru.kpfu.itis.android.core.analytics.AnalyticsTracker
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    @Singleton
    fun provideFirebaseAnalytics(
        @ApplicationContext context: Context
    ): FirebaseAnalytics =
        FirebaseAnalytics.getInstance(context)

    @Provides
    @Singleton
    fun provideAnalyticsTracker(
        impl: FirebaseAnalyticsTracker
    ): AnalyticsTracker = impl
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