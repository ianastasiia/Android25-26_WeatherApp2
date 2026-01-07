package ru.kpfu.itis.android.feature.search_city.impl.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class CitySearchResponseDto(
    val id: Int? = null,
    val name: String,
    val region: String? = null,
    val country: String,
    val lat: Double,
    val lon: Double,
    val url: String? = null
)
