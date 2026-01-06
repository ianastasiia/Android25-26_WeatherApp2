package ru.kpfu.itis.android.feature.forecast.impl.data.mapper

import ru.kpfu.itis.android.feature.forecast.api.domain.model.ForecastDay
import ru.kpfu.itis.android.feature.forecast.impl.data.remote.ForecastDayDto

object ForecastMapper {
    fun map(dto: ForecastDayDto): ForecastDay =
        ForecastDay(
            date = dto.date,
            avgTempC = dto.day.avgTempC.toInt(),
            condition = dto.day.condition.text,
            iconUrl = "https:${dto.day.condition.icon}"
        )
}