package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.papalote.ui.theme.components.CustomBottomBar
import com.example.papalote.ui.theme.components.ZoneHeader
import com.example.papalote.utils.TokenManager
import com.example.papalote.ui.theme.components.ZoneDetailScreen
import com.example.papalote.R

@Composable
fun PequeñosScreen(
    navController: NavHostController,
    tokenManager: TokenManager,
    zoneName: String, // Agregar este parámetro
) {
    ZoneDetailScreen(
        zoneName = "Pequeños",
        backgroundColor = Color(0xFF008C95),
        headerImage = R.drawable.pequenoslogo,
        activities = listOf("Actividad Pequeños A", "Actividad Pequeños B", "Actividad Pequeños C"),
        onMedalClick = { /* Acción específica para medallas en Pequeños */ },
        navController = navController,
        tokenManager = tokenManager,
        onBack = { navController.navigateUp() },
        onActivityClick = { activityName ->
            // Define the navigation or action when an activity is clicked
            navController.navigate("comunico_questions/$activityName")
        }
    )
}