package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.papalote.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    // Navegar automáticamente después de 3 segundos
    LaunchedEffect(key1 = true) {
        delay(3000) // Espera 5 segundos
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true } // Elimina la pantalla de splash del back stack
        }
    }

    // Diseño de la pantalla Splash
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4CA6A8)), // Color de fondo similar a la imagen
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Texto "Explora"
            Text(
                text = "EXPLORA",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            // Imagen del logo
            Image(
                painter = painterResource(id = R.drawable.papalotelogoo),
                contentDescription = "Logo del Museo Papalote",
                modifier = Modifier.size(200.dp)
            )
            // Texto "Monterrey"
            Text(
                text = "MONTERREY",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}