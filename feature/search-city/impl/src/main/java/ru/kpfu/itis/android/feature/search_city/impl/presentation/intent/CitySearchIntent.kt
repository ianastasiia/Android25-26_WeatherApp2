package ru.kpfu.itis.android.feature.search_city.impl.presentation.intent

import ru.kpfu.itis.android.core.mvi.UiIntent
import ru.kpfu.itis.android.feature.search_city.api.domain.model.City

sealed interface CitySearchIntent : UiIntent {
    data class QueryChanged(val value: String) : CitySearchIntent
    data class CitySelected(val city: City) : CitySearchIntent

}