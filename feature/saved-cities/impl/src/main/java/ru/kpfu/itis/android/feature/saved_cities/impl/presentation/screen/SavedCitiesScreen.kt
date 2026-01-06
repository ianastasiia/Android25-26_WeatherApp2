package ru.kpfu.itis.android.feature.saved_cities.impl.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ru.kpfu.itis.android.feature.saved_cities.impl.presentation.intent.SavedCitiesIntent
import ru.kpfu.itis.android.feature.saved_cities.impl.presentation.viewmodel.SavedCitiesViewModel

@Composable
fun SavedCitiesScreen(
    viewModel: SavedCitiesViewModel = hiltViewModel(),
    onCityClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendIntent(SavedCitiesIntent.Load)
    }

    Scaffold { padding ->
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(state.cities) { city ->
                    ListItem(
                        headlineContent = { Text(text = city.name) },
                        modifier = Modifier.clickable {
                            onCityClick(city.name)
                        },
                        trailingContent = {
                            IconButton(onClick = {
                                viewModel.sendIntent(
                                    SavedCitiesIntent.Delete(city.name)
                                )
                            }) {
                                Icon(Icons.Default.Delete, null)
                            }
                        }
                    )
                }
            }
        }
    }
}
