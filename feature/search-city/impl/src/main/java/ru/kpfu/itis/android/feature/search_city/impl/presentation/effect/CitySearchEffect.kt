package ru.kpfu.itis.android.feature.search_city.impl.presentation.effect

import ru.kpfu.itis.android.core.mvi.UiEffect
import ru.kpfu.itis.android.feature.search_city.api.domain.model.City

sealed interface CitySearchEffect : UiEffect {

    data class CityChosen(val city: City) : CitySearchEffect
    data class ShowError(val message: String) : CitySearchEffect

}