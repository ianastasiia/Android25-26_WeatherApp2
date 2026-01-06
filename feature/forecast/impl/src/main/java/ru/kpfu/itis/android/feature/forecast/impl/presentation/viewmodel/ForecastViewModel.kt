package ru.kpfu.itis.android.feature.forecast.impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.kpfu.itis.android.core.mvi.MviViewModel
import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.feature.forecast.api.domain.usecase.GetForecastUseCase
import ru.kpfu.itis.android.feature.forecast.impl.presentation.effect.ForecastEffect
import ru.kpfu.itis.android.feature.forecast.impl.presentation.intent.ForecastIntent
import ru.kpfu.itis.android.feature.forecast.impl.presentation.state.ForecastState
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecast: GetForecastUseCase
) : MviViewModel<
        ForecastIntent,
        ForecastState,
        ForecastEffect
        >(initialState = ForecastState()) {

    override fun handleIntent(intent: ForecastIntent) {
        when (intent) {
            is ForecastIntent.Load -> load(intent.query)
        }
    }

    private fun load(query: String) {
        viewModelScope.launch {
            setState { copy(isLoading = true) }

            when (val result = getForecast(query)) {
                is ApiResult.Success -> setState {
                    copy(
                        isLoading = false,
                        days = result.data
                    )
                }

                is ApiResult.Failure -> {
                    setState { copy(isLoading = false) }
                    sendEffect(
                        ForecastEffect.ShowError(
                            result.throwable.message ?: "Failed to load forecast"
                        )
                    )
                }
            }
        }
    }
}