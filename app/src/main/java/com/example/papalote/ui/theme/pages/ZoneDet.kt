package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.papalote.R
import com.example.papalote.ui.theme.components.CustomBottomBar
import com.example.papalote.utils.LanguageManager
import com.example.papalote.utils.TokenManager

@Composable
fun ZoneDetailScreen(
    zoneName: String,
    onBack: () -> Unit,
    navController: NavHostController,
    onMedalClick: () -> Unit,
    tokenManager: TokenManager
) {
    Scaffold(
        bottomBar = {
            CustomBottomBar(navController = navController, tokenManager = tokenManager)
        }
    ) { paddingValues: PaddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                HeaderWithLogo()
                Header(zoneName = zoneName)

                ActivitiesSection(navController = navController, zoneName = zoneName)

                MedalSection(zoneName = zoneName, onMedalClick = onMedalClick)
            }
        }
    }
}

@Composable
fun HeaderWithLogo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFC2D401))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tocojuegoyaprendo),
            contentDescription = "Logo del Museo",
            modifier = Modifier.width(270.dp)
        )
    }
}

@Composable
fun Header(zoneName: String) {
    // Definir los textos según el idioma seleccionado
    val activitiesText = if (LanguageManager.language == "es") "Actividades del día" else "Today's Activities"

    val titleColor = when (zoneName) {
        "Comunico" -> Color(0xFF016ED2)
        "Pertenezco" -> Color(0xFFC2D401)
        "Expreso" -> Color(0xFFFF6C01)
        "Soy" -> Color(0xFFDD0633)
        "Comprendo" -> Color(0xFF7C55C7)
        "Pequeños" -> Color(0xFF008C95)
        else -> Color.Black
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = zoneName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = titleColor,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = activitiesText,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun ActivitiesSection(navController: NavHostController, zoneName: String) {
    // Definir los textos según el idioma seleccionado
    val activity1Text = if (LanguageManager.language == "es") "Actividad 1" else "Activity 1"
    val activity2Text = if (LanguageManager.language == "es") "Actividad 2" else "Activity 2"
    val activity3Text = if (LanguageManager.language == "es") "Actividad 3" else "Activity 3"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        ActivityCard(
            color = Color(0xFFE6A957),
            icon = painterResource(id = R.drawable.calendario),
            text = activity1Text,
            onClick = { navController.navigate("dinosaur/$zoneName") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ActivityCard(
            color = Color(0xFF6AB98D),
            icon = painterResource(id = R.drawable.calendario),
            text = activity2Text,
            onClick = { navController.navigate("dinosaur/$zoneName") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ActivityCard(
            color = Color(0xFF55A8E2),
            icon = painterResource(id = R.drawable.calendario),
            text = activity3Text,
            onClick = { navController.navigate("dinosaur/$zoneName") }
        )
    }
}

@Composable
fun ActivityCard(color: Color, icon: Painter, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(34.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun MedalSection(zoneName: String, onMedalClick: () -> Unit) {
    val backgroundImage = when (zoneName) {
        "Comprendo" -> R.drawable.comprendologo
        "Comunico" -> R.drawable.comunicologo
        "Expreso" -> R.drawable.expresologo
        "Pequeños" -> R.drawable.pequenoslogo
        "Pertenezco" -> R.drawable.pertenezcologo
        "Soy" -> R.drawable.soylogo
        else -> R.drawable.soylogo
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(Color(0x80000000))
    ) {
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(4) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    repeat(4) {
                        Image(
                            painter = painterResource(id = R.drawable.medalla),
                            contentDescription = "Medalla",
                            modifier = Modifier
                                .size(68.dp)
                                .clickable { onMedalClick() }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}