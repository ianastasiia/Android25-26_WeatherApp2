package ru.kpfu.itis.android.feature.forecast.impl.presentation.intent

import ru.kpfu.itis.android.core.mvi.UiIntent


sealed interface ForecastIntent : UiIntent {
    data class Load(val query: String) : ForecastIntent
}