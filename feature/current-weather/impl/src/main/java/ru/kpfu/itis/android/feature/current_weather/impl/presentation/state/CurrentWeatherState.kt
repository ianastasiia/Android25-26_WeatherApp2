package ru.kpfu.itis.android.feature.current_weather.impl.presentation.state

import ru.kpfu.itis.android.core.mvi.UiState
import ru.kpfu.itis.android.feature.current_weather.api.domain.model.CurrentWeather

data class CurrentWeatherState(
    val isLoading: Boolean = false,
    val weather: CurrentWeather? = null,
    val error: String? = null
) : UiState
