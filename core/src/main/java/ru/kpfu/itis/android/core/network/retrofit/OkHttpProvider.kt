package ru.kpfu.itis.android.core.network.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.kpfu.itis.android.core.network.interceptor.ApiKeyInterceptor

object OkHttpProvider {
    fun provide(
        apiKey: String,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(ApiKeyInterceptor(apiKey))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

}