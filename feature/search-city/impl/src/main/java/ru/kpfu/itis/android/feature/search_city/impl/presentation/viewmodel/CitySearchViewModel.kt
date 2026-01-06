package ru.kpfu.itis.android.feature.search_city.impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.kpfu.itis.android.core.mvi.MviViewModel
import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.feature.search_city.api.domain.usecase.SearchCityUseCase
import ru.kpfu.itis.android.feature.search_city.impl.presentation.effect.CitySearchEffect
import ru.kpfu.itis.android.feature.search_city.impl.presentation.intent.CitySearchIntent
import ru.kpfu.itis.android.feature.search_city.impl.presentation.state.CitySearchState
import javax.inject.Inject

@HiltViewModel
class CitySearchViewModel @Inject constructor(
    private val citySearchCityUseCase: SearchCityUseCase
) : MviViewModel<CitySearchIntent, CitySearchState, CitySearchEffect>(
    initialState = CitySearchState()
) {
    override fun handleIntent(intent: CitySearchIntent) {
        when (intent) {
            is CitySearchIntent.QueryChanged -> {
                search(query = intent.value)
            }

            is CitySearchIntent.CitySelected -> {
                sendEffect(effect = CitySearchEffect.CityChosen(intent.city))
            }
        }
    }

    private fun search(query: String) {
        if (query.length < 2) return

        viewModelScope.launch {
            setState {
                copy(
                    query = query,
                    isLoading = true
                )
            }

            when (val result = citySearchCityUseCase(query = query)) {
                is ApiResult.Success -> {
                    setState {
                        copy(
                            cities = result.data,
                            isLoading = false
                        )
                    }
                }

                is ApiResult.Failure -> {
                    setState {
                        copy(
                            isLoading = false
                        )
                    }
                    sendEffect(CitySearchEffect.ShowError("Search failed"))
                }
            }
        }
    }

}