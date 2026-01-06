package ru.kpfu.itis.android.feature.search_city.impl.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ru.kpfu.itis.android.feature.search_city.api.domain.model.City
import ru.kpfu.itis.android.feature.search_city.impl.presentation.effect.CitySearchEffect
import ru.kpfu.itis.android.feature.search_city.impl.presentation.intent.CitySearchIntent
import ru.kpfu.itis.android.feature.search_city.impl.presentation.viewmodel.CitySearchViewModel

@Composable
fun SearchCityScreen(
    viewModel: CitySearchViewModel = hiltViewModel(), onCitySelected: (City) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CitySearchEffect.CityChosen -> {
                    onCitySelected(effect.city)
                }

                is CitySearchEffect.ShowError -> {
                    snackBarHostState.showSnackbar(effect.message)
                }
            }

        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = state.query, onValueChange = {
                    viewModel.sendIntent(CitySearchIntent.QueryChanged(it))
                }, label = { Text("Search city") }, modifier = Modifier.fillMaxSize()
            )

            Spacer(Modifier.height(16.dp))

            if (state.isLoading) {
                CircularProgressIndicator()
            }

            LazyColumn {
                items(state.cities) { city ->
                    ListItem(
                        headlineContent = { Text(text = city.name) },
                        supportingContent = { Text(text = city.country) },
                        modifier = Modifier.clickable {
                            viewModel.sendIntent(
                                CitySearchIntent.CitySelected(city)
                            )
                        }
                    )
                }
            }
        }

    }

}