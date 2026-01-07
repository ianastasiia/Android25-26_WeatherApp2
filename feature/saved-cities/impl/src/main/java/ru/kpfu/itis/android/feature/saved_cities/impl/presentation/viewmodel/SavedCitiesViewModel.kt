package ru.kpfu.itis.android.feature.saved_cities.impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.kpfu.itis.android.core.analytics.AnalyticsTracker
import ru.kpfu.itis.android.core.mvi.MviViewModel
import ru.kpfu.itis.android.feature.saved_cities.api.domain.model.SavedCity
import ru.kpfu.itis.android.feature.saved_cities.api.domain.usecase.DeleteCityUseCase
import ru.kpfu.itis.android.feature.saved_cities.api.domain.usecase.GetSavedCitiesUseCase
import ru.kpfu.itis.android.feature.saved_cities.api.domain.usecase.SaveCityUseCase
import ru.kpfu.itis.android.feature.saved_cities.impl.presentation.effect.SavedCitiesEffect
import ru.kpfu.itis.android.feature.saved_cities.impl.presentation.intent.SavedCitiesIntent
import ru.kpfu.itis.android.feature.saved_cities.impl.presentation.state.SavedCitiesState
import javax.inject.Inject

@HiltViewModel
class SavedCitiesViewModel @Inject constructor(
    private val observeSavedCities: GetSavedCitiesUseCase,
    private val saveCity: SaveCityUseCase,
    private val deleteCity: DeleteCityUseCase,
    analyticsTracker: AnalyticsTracker,
) : MviViewModel<SavedCitiesIntent, SavedCitiesState, SavedCitiesEffect
        >(initialState = SavedCitiesState()) {

    init {
        analyticsTracker.trackScreen("save_city")
    }

    override fun handleIntent(intent: SavedCitiesIntent) {
        when (intent) {
            is SavedCitiesIntent.Load -> observe()
            is SavedCitiesIntent.Delete -> delete(intent.cityName)
            is SavedCitiesIntent.Add -> add(intent.city)
        }
    }

    private fun observe() {
        viewModelScope.launch {
            observeSavedCities().collectLatest { cities ->
                setState {
                    copy(
                        cities = cities,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun delete(name: String) {
        viewModelScope.launch {
            deleteCity(name)
        }
    }

    private fun add(city: SavedCity) {
        viewModelScope.launch {
            saveCity(city)
        }
    }
}