package ru.kpfu.itis.android.feature.current_weather.impl.presentation.intent

import ru.kpfu.itis.android.core.mvi.UiIntent

sealed interface CurrentWeatherIntent : UiIntent {

    data class Load(val query: String) : CurrentWeatherIntent

}