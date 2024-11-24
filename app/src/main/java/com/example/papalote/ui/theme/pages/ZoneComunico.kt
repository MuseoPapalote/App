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
fun ComunicoScreen(
    navController: NavHostController,
    tokenManager: TokenManager,
    zoneName: String, // Agregar este parámetro
) {
    ZoneDetailScreen(
        zoneName = "Comunico",
        backgroundColor = Color(0xFF016ED2),
        headerImage = R.drawable.comunicologo,
        activities = listOf("Actividad Comunicación A", "Actividad Comunicación B", "Actividad Comunicación C"),
        onMedalClick = { /* Acción específica para medallas en Comunico */ },
        navController = navController,
        tokenManager = tokenManager,
        onBack = { navController.navigateUp() },
        onActivityClick = { activityName ->
            // Define the navigation or action when an activity is clicked
            navController.navigate("comunico_questions/$activityName")
        }

    )
}