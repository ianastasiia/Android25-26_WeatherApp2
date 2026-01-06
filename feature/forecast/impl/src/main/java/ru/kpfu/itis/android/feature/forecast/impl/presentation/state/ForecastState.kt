package ru.kpfu.itis.android.feature.forecast.impl.presentation.state

import ru.kpfu.itis.android.core.mvi.UiState
import ru.kpfu.itis.android.feature.forecast.api.domain.model.ForecastDay

data class ForecastState(
    val isLoading: Boolean = false,
    val days: List<ForecastDay> = emptyList()
) : UiState