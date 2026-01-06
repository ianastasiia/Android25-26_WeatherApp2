package ru.kpfu.itis.android.feature.search_city.impl.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
)
