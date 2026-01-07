package ru.kpfu.itis.android.feature.search_city.impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.kpfu.itis.android.core.mvi.MviViewModel
import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.feature.search_city.api.domain.usecase.SearchCityUseCase
import ru.kpfu.itis.android.feature.search_city.impl.presentation.effect.CitySearchEffect
import ru.kpfu.itis.android.feature.search_city.impl.presentation.intent.CitySearchIntent
import ru.kpfu.itis.android.feature.search_city.impl.presentation.state.CitySearchState
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class CitySearchViewModel @Inject constructor(
    private val citySearchCityUseCase: SearchCityUseCase
) : MviViewModel<CitySearchIntent, CitySearchState, CitySearchEffect>(
    initialState = CitySearchState()
) {
    private var searchJob: Job? = null
    private var lastSearchId = 0

    override fun handleIntent(intent: CitySearchIntent) {
        when (intent) {
            is CitySearchIntent.QueryChanged -> {
                setState {
                    copy(
                        query = intent.value,
                        cities = if (intent.value.length < 2) emptyList() else cities,
                        isLoading = intent.value.trim().length >= 2
                    )
                }

                val currentSearchId = ++lastSearchId
                searchJob?.cancel()

                if (intent.value.trim().length >= 2) {
                    searchJob = viewModelScope.launch {
                        try {
                            delay(500)

                            if (currentSearchId == lastSearchId) {
                                search(query = intent.value.trim())
                            }
                        } catch (_: CancellationException) {
                            setState {
                                copy(isLoading = false)
                            }
                        }
                    }
                } else {
                    setState {
                        copy(isLoading = false)
                    }
                }
            }

            is CitySearchIntent.CitySelected -> {
                sendEffect(effect = CitySearchEffect.CityChosen(intent.city))
            }
        }
    }

    private suspend fun search(query: String) {
        if (query.length < 2) return

        setState {
            copy(isLoading = true)
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

                val errorMessage = when {
                    result.throwable.message?.contains("timeout", ignoreCase = true) == true ->
                        "Timeout: Check your internet connection"

                    result.throwable.message?.contains(
                        "Unable to resolve host",
                        ignoreCase = true
                    ) == true ->
                        "No internet connection"

                    result.throwable.message?.contains("404", ignoreCase = true) == true ->
                        "API endpoint not found. Check URL"

                    result.throwable.message?.contains("403", ignoreCase = true) == true ->
                        "Invalid API key"

                    else -> "Search failed: ${result.throwable.message}"
                }

                sendEffect(CitySearchEffect.ShowError(errorMessage))
            }
        }
    }

}