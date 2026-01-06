package ru.kpfu.itis.android.feature.current_weather.impl.presentation.effect

import ru.kpfu.itis.android.core.mvi.UiEffect

sealed interface CurrentWeatherEffect: UiEffect {

    data class ShowError(val message: String): CurrentWeatherEffect

}