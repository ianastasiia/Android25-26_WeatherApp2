package ru.kpfu.itis.android.feature.saved_cities.impl.presentation.intent

import ru.kpfu.itis.android.core.mvi.UiIntent
import ru.kpfu.itis.android.feature.saved_cities.api.domain.model.SavedCity

sealed interface SavedCitiesIntent : UiIntent {
    data object Load : SavedCitiesIntent
    data class Add(val city: SavedCity) : SavedCitiesIntent
    data class Delete(val cityName: String) : SavedCitiesIntent
}