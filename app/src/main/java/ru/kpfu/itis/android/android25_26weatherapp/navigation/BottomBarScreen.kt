package ru.kpfu.itis.android.android25_26weatherapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Search : BottomBarScreen(
        route = "search_city",
        title = "Search",
        icon = Icons.Default.Search
    )

    data object Saved : BottomBarScreen(
        route = "saved_cities",
        title = "Saved",
        icon = Icons.Default.Favorite
    )
}