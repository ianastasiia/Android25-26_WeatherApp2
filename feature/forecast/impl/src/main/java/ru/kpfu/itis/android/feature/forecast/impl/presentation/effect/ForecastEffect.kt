package ru.kpfu.itis.android.feature.forecast.impl.presentation.effect

import ru.kpfu.itis.android.core.mvi.UiEffect


sealed interface ForecastEffect : UiEffect {
    data class ShowError(val message: String) : ForecastEffect
}