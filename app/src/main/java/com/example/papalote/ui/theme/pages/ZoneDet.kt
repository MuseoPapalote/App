package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papalote.R

@Composable
fun ZoneDetailScreen(zoneName: String, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Barra de encabezado
            Header(zoneName = zoneName)

            // Sección de actividades
            ActivitiesSection()

            // Sección de medallas
            MedalSection()

            // Barra de navegación inferior
            BottomNavigationBar(onBack = onBack)
        }
    }
}

@Composable
fun Header(zoneName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E600)), // Color de fondo de encabezado
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = zoneName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "Actividades del día",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun ActivitiesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        ActivityCard(color = Color(0xFFE6A957), icon = Icons.Default.Star, text = "Actividad 1")
        Spacer(modifier = Modifier.height(8.dp))
        ActivityCard(color = Color(0xFF6AB98D), icon = Icons.Default.Star, text = "Actividad 2")
        Spacer(modifier = Modifier.height(8.dp))
        ActivityCard(color = Color(0xFF55A8E2), icon = Icons.Default.Star, text = "Actividad 3")
    }
}

@Composable
fun ActivityCard(color: Color, icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color, shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
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
fun MedalSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Filas de medallas (ejemplo de 3x4)
        repeat(3) { rowIndex ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                repeat(4) {
                    Icon(
                        imageVector = Icons.Default.Star, // Cambia el icono según el diseño
                        contentDescription = null,
                        tint = Color(0xFFFFD700), // Color dorado
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun BottomNavigationBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFB8C94A)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
        androidx.compose.material.IconButton(onClick = {/*Acción para Home*/ }) {
            Image(
                painter = painterResource(id = R.drawable.hoome),
                contentDescription = "Home",
                modifier = Modifier.size(48.dp)
            )
        }
        androidx.compose.material.IconButton(onClick = {/*Acción para camara*/ }) {
            Image(
                painter = painterResource(id = R.drawable.qrs),
                contentDescription = "Camara",
                modifier = Modifier.size(48.dp)

            )
        }

    }
}
