package com.example.papalote.ui.theme.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.papalote.R

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Add

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


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
                .clip(CircleShape) // Forma circular para el botón flotante
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    // Ícono para "Perfil"
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile), // Ícono real de "Perfil"
                        contentDescription = "Perfil",
                        tint = Color(0xFFA4CD39), // Color verde similar al diseño
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Perfil",
                        color = Color.Black,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
                    )
                }
            }
            DropdownMenuItem(onClick = {
                navController.navigate("badges")
                isMenuExpanded = false
            }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    // Ícono para "Insignias"
                    Icon(
                        painter = painterResource(id = R.drawable.ic_badges), // Ícono real de "Insignias"
                        contentDescription = "Insignias",
                        tint = Color(0xFFA4CD39),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Insignias",
                        color = Color.Black,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
                    )
                }
            }
            DropdownMenuItem(onClick = {
                navController.navigate("zones")
                isMenuExpanded = false
            }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    // Ícono para "Zonas"
                    Icon(
                        painter = painterResource(id = R.drawable.ic_zones), // Ícono real de "Zonas"
                        contentDescription = "Zonas",
                        tint = Color(0xFFA4CD39),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Zonas",
                        color = Color.Black,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
                    )
                }
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
            // Ícono de Buscar
            IconButton(onClick = { navController.navigate("search") }) {
                Icon(
                    imageVector = Icons.Default.Search, // Ícono predeterminado de Material Icons
                    contentDescription = "Buscar",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            // Ícono personalizado de Home
            IconButton(onClick = { navController.navigate("home") }) {
                Image(
                    painter = painterResource(id = R.drawable.hoome), // Ícono personalizado de "Inicio"
                    contentDescription = "Home",
                    modifier = Modifier.size(48.dp)
                )
            }

            // Ícono personalizado de QR
            IconButton(onClick = { navController.navigate("scanQR") }) {
                Image(
                    painter = painterResource(id = R.drawable.qrs), // Ícono personalizado de "Escaneo QR"
                    contentDescription = "Escaneo QR",
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}
