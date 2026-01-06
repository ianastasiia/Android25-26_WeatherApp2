package ru.kpfu.itis.android.feature.search_city.impl.presentation.state

import ru.kpfu.itis.android.core.mvi.UiState
import ru.kpfu.itis.android.feature.search_city.api.domain.model.City

data class CitySearchState(
    val query: String = "",
    val isLoading: Boolean = false,
    val cities: List<City> = emptyList(),
) : UiState
