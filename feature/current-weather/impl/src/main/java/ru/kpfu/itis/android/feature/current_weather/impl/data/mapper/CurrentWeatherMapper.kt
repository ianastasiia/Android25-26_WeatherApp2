package ru.kpfu.itis.android.feature.current_weather.impl.data.mapper

import ru.kpfu.itis.android.feature.current_weather.api.domain.model.CurrentWeather
import ru.kpfu.itis.android.feature.current_weather.impl.data.remote.CurrentWeatherDto

object CurrentWeatherMapper {

    fun map(dto: CurrentWeatherDto): CurrentWeather =
        CurrentWeather(
            cityName = dto.location.name,
            temperatureInCelsius = dto.current.tempC.toInt(),
            description = dto.current.condition.text,
            latitude = dto.location.lat,
            longitude = dto.location.lon,
        )
}