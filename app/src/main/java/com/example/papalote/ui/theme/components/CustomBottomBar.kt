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
import com.example.papalote.utils.TokenManager

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Add

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CustomBottomBar(
    tokenManager: TokenManager, // Agregamos TokenManager como parámetro
    navController: NavHostController
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Barra inferior con los 3 botones principales
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFF6A0DAD)) // Fondo morado
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícono de Mapa
            IconButton(onClick = { navController.navigate("mapa") }) { // Cambiamos "search" por "mapa"
                Icon(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = "Mapa",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            // Ícono de Home (redirige a la página de Zonas)
            IconButton(onClick = { navController.navigate("zones") }) {
                Image(
                    painter = painterResource(id = R.drawable.hoome),
                    contentDescription = "Home",
                    modifier = Modifier.size(48.dp)
                )
            }

            // Ícono de QR
            IconButton(onClick = { navController.navigate("scanQR") }) {
                Image(
                    painter = painterResource(id = R.drawable.qrs),
                    contentDescription = "Escaneo QR",
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        // Botón flotante "+" (más pequeño)
        FloatingActionButton(
            onClick = { isMenuExpanded = true },
            backgroundColor = Color(0xFF0033CC), // Azul para el botón flotante
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-40).dp) // Ajustar la posición del botón flotante
                .size(60.dp) // Tamaño reducido del botón flotante
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar",
                tint = Color.Yellow
            )
        }

        // DropdownMenu desplegable
        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false }
        ) {
            DropdownMenuItem(onClick = {
                navController.navigate("profile")
                isMenuExpanded = false
            }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Perfil",
                        tint = Color(0xFFA4CD39),
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
                    Icon(
                        painter = painterResource(id = R.drawable.ic_badges),
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
                    Icon(
                        painter = painterResource(id = R.drawable.ic_zones),
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
            // Nuevo botón de "Cerrar sesión"
            DropdownMenuItem(onClick = {
                tokenManager.clearToken() // Limpiar el token del usuario
                navController.navigate("welcome") {
                    popUpTo("zones") { inclusive = true } // Aseguramos que el stack se limpie
                }
                isMenuExpanded = false
            }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logout), // Ícono de logout
                        contentDescription = "Cerrar sesión",
                        tint = Color.Red,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Cerrar sesión",
                        color = Color.Black,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
                    )
                }
            }
        }
    }
}