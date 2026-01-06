package ru.kpfu.itis.android.feature.saved_cities.impl.presentation.effect

import ru.kpfu.itis.android.core.mvi.UiEffect

sealed interface SavedCitiesEffect : UiEffect {
    data class OpenCity(val cityName: String) : SavedCitiesEffect
}