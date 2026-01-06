package ru.kpfu.itis.android.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitProvider {

    fun provide(
        baseUrl: String, client: OkHttpClient
    ): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        return Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        ).build()
    }
}