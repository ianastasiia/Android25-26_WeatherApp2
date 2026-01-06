package ru.kpfu.itis.android.feature.forecast.impl.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import ru.kpfu.itis.android.feature.forecast.impl.presentation.intent.ForecastIntent
import ru.kpfu.itis.android.feature.forecast.impl.presentation.viewmodel.ForecastViewModel

@Composable
fun ForecastScreen(
    city: String,
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(city) {
        viewModel.sendIntent(
            ForecastIntent.Load(city)
        )
    }

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            LazyColumn {
                items(state.days) { day ->
                    ListItem(
                        leadingContent = {
                            AsyncImage(
                                model = day.iconUrl,
                                contentDescription = null
                            )
                        },
                        headlineContent = { Text(day.date) },
                        supportingContent = {
                            Text("${day.avgTempC}°C • ${day.condition}")
                        }
                    )
                }
            }
        }
    }
}
