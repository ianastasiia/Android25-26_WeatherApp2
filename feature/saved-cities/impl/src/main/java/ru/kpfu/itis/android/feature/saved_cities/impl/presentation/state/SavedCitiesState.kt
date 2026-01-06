package ru.kpfu.itis.android.feature.saved_cities.impl.presentation.state

import ru.kpfu.itis.android.core.mvi.UiState
import ru.kpfu.itis.android.feature.saved_cities.api.domain.model.SavedCity

data class SavedCitiesState(
    val cities: List<SavedCity> = emptyList(),
    val isLoading: Boolean = false
) : UiState
