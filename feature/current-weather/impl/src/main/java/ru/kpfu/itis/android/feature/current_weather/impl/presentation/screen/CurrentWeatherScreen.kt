package ru.kpfu.itis.android.feature.current_weather.impl.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ru.kpfu.itis.android.feature.current_weather.impl.presentation.effect.CurrentWeatherEffect
import ru.kpfu.itis.android.feature.current_weather.impl.presentation.intent.CurrentWeatherIntent
import ru.kpfu.itis.android.feature.current_weather.impl.presentation.viewmodel.CurrentWeatherViewModel

@Composable
fun CurrentWeatherScreen(
    city: String,
    viewModel: CurrentWeatherViewModel = hiltViewModel(),
    onForecastClick: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(city) {
        viewModel.sendIntent(
            CurrentWeatherIntent.Load(query = city)
        )
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CurrentWeatherEffect.ShowError -> {
                    snackBarHostState.showSnackbar(
                        message = effect.message
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator()
                }

                state.weather != null -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = state.weather!!.cityName,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${state.weather!!.temperatureInCelsius}°C",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = state.weather!!.description,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = onForecastClick) {
                            Text("View forecast")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                viewModel.sendIntent(CurrentWeatherIntent.SaveCity)
                            }
                        ) {
                            Text("Add to favorites")
                        }

                    }
                }

                else -> {
                    Text("No data")
                }

            }
        }
    }


}