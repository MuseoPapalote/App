package com.example.papalote.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CustomBottomBar(
    navController: NavHostController // NavController para manejar la navegación
) {
    var isMenuExpanded by remember { mutableStateOf(false) } // Controla el estado del DropdownMenu

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(50.dp)) // Forma ovalada para el fondo
            .background(Color(0xFF6A0DAD)), // Fondo morado
        contentAlignment = Alignment.Center
    ) {
        // Botón flotante "+"
        FloatingActionButton(
            onClick = { isMenuExpanded = true },
            backgroundColor = Color(0xFF0033CC), // Azul para el botón flotante
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-30).dp) // Elevar el botón flotante
                .clip(RoundedCornerShape(50.dp)) // Forma ovalada para el botón
                .size(70.dp) // Tamaño ajustado del botón
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Menu",
                tint = Color.Yellow
            )
        }

        // DropdownMenu desplegable
        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false } // Cierra el menú al hacer clic fuera
        ) {
            DropdownMenuItem(onClick = {
                navController.navigate("profile")
                isMenuExpanded = false
            }) {
                Text("Perfil")
            }
            DropdownMenuItem(onClick = {
                navController.navigate("badges")
                isMenuExpanded = false
            }) {
                Text("Insignias")
            }
            DropdownMenuItem(onClick = {
                navController.navigate("zones")
                isMenuExpanded = false
            }) {
                Text("Zonas")
            }
        }

        // Barra inferior con los 3 botones principales
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 12.dp), // Espaciado para ajustar dentro del fondo ovalado
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("search") }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar", tint = Color.White)
            }
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Inicio", tint = Color.White)
            }
            IconButton(onClick = { navController.navigate("scanQR") }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Escaneo QR", tint = Color.White)
            }
        }
    }
}
