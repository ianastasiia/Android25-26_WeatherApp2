package ru.kpfu.itis.android.feature.current_weather.impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.kpfu.itis.android.core.database.entity.CityEntity
import ru.kpfu.itis.android.core.domain.usecase.SaveCityUseCase
import ru.kpfu.itis.android.core.mvi.MviViewModel
import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.feature.current_weather.api.domain.usecase.GetCurrentWeatherUseCase
import ru.kpfu.itis.android.feature.current_weather.impl.presentation.effect.CurrentWeatherEffect
import ru.kpfu.itis.android.feature.current_weather.impl.presentation.intent.CurrentWeatherIntent
import ru.kpfu.itis.android.feature.current_weather.impl.presentation.state.CurrentWeatherState
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val saveCityUseCase: SaveCityUseCase
//    private val analytics: FirebaseAnalytics
) : MviViewModel<
        CurrentWeatherIntent,
        CurrentWeatherState,
        CurrentWeatherEffect
        >(
    initialState = CurrentWeatherState()
) {
    override fun handleIntent(intent: CurrentWeatherIntent) {
        when (intent) {
            is CurrentWeatherIntent.Load -> loadWeather(intent.query)

            is CurrentWeatherIntent.SaveCity -> saveCurrentCity()
        }
    }

    private fun loadWeather(query: String) {
        viewModelScope.launch {
//            firebaseAnalytics.logEvent(
//                "current_weather_screen_opened",
//                null
//            )

            setState {
                copy(
                    isLoading = true,
                    error = null
                )
            }

            when (val result = getCurrentWeatherUseCase(query)) {
                is ApiResult.Success -> setState {
                    copy(
                        isLoading = false,
                        weather = result.data
                    )
                }

                is ApiResult.Failure -> {
                    setState {
                        copy(
                            isLoading = false,
                        )
                    }

                    sendEffect(
                        CurrentWeatherEffect.ShowError(
                            message = result.throwable.message ?: "Failed to load weather"
                        )
                    )
                }
            }
        }
    }

    private fun saveCurrentCity() {
        val weather = state.value.weather ?: return

        viewModelScope.launch {
            saveCityUseCase(
                CityEntity(
                    name = weather.cityName,
                    latitude = weather.latitude,
                    longitude = weather.longitude
                )
            )
            sendEffect(CurrentWeatherEffect.ShowError("City saved"))
        }
    }
}