package ru.kpfu.itis.android.feature.saved_cities.impl.presentation.intent

import ru.kpfu.itis.android.core.mvi.UiIntent

sealed interface SavedCitiesIntent : UiIntent {
    data object Load : SavedCitiesIntent
    data class Delete(val cityName: String) : SavedCitiesIntent
    data class OpenCity(val cityName: String) : SavedCitiesIntent
}