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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Add

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.papalote.EncuestaRequest
import com.example.papalote.viewModel.EncuestaViewModel
import com.example.papalote.viewModelFactory.EncuestaViewModelFactory
import com.example.papalote.api.Repository
import com.example.papalote.RetrofitClient
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun CustomBottomBar(
    tokenManager: TokenManager,
    navController: NavHostController,
) {
    // Instancia del EncuestaViewModel
    val encuestaViewModel: EncuestaViewModel = viewModel(
        factory = EncuestaViewModelFactory(
            repository = Repository(
                apiService = RetrofitClient.apiService,
                tokenManager = tokenManager
            )
        )
    )

    var isMenuExpanded by remember { mutableStateOf(false) }
    var showSurveyDialog by remember { mutableStateOf(false) } // Controla la visibilidad del popup

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
            IconButton(onClick = { navController.navigate("mapa") }) {
                Icon(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = "Mapa",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            // Ícono de Home
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

        // Botón flotante "+"
        FloatingActionButton(
            onClick = { isMenuExpanded = true },
            backgroundColor = Color(0xFF0033CC),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-40).dp)
                .size(60.dp)
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
                MenuItemRow(iconRes = R.drawable.ic_profile, text = "Perfil", color = Color(0xFFA4CD39))
            }

            DropdownMenuItem(onClick = {
                navController.navigate("badges")
                isMenuExpanded = false
            }) {
                MenuItemRow(iconRes = R.drawable.ic_badges, text = "Insignias", color = Color(0xFFA4CD39))
            }

            DropdownMenuItem(onClick = {
                navController.navigate("zones")
                isMenuExpanded = false
            }) {
                MenuItemRow(iconRes = R.drawable.ic_zones, text = "Zonas", color = Color(0xFFA4CD39))
            }

            // Botón de "Cerrar sesión"
            DropdownMenuItem(onClick = {
                showSurveyDialog = true // Muestra el popup
                isMenuExpanded = false
            }) {
                MenuItemRow(iconRes = R.drawable.logout, text = "Cerrar sesión", color = Color.Red)
            }
        }
    }

    // Encuesta de calificación
    if (showSurveyDialog) {
        SurveyDialog(
            onDismiss = { showSurveyDialog = false },
            onSubmit = { rating, comment ->
                tokenManager.clearToken() // Limpia el token del usuario
                navController.navigate("welcome") {
                    popUpTo("zones") { inclusive = true }
                }
                showSurveyDialog = false
                Log.d("Encuesta", "Calificación: $rating, Comentario: $comment")
            },
            encuestaViewModel = encuestaViewModel // Aquí pasamos el ViewModel
        )
    }
}

@Composable
fun MenuItemRow(iconRes: Int, text: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = Color.Black,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
        )
    }
}

@Composable
fun SurveyDialog(
    onDismiss: () -> Unit,
    onSubmit: (Int, String) -> Unit,
    encuestaViewModel: EncuestaViewModel // Agregar ViewModel
) {
    var rating by remember { mutableStateOf(0) }
    var comment by remember { mutableStateOf("") }
    val encuestaState by encuestaViewModel.encuestaState.collectAsState()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Encuesta") },
        text = {
            Column {
                Text("Calificación general de la visita (1-5):")
                Row {
                    (1..5).forEach { star ->
                        IconButton(onClick = { rating = star }) {
                            Icon(
                                imageVector = if (star <= rating) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = "Estrella $star",
                                tint = if (star <= rating) Color.Yellow else Color.Gray,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Comentario:")
                TextField(
                    value = comment,
                    onValueChange = { comment = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Escribe un comentario...") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                if (rating > 0 && comment.isNotEmpty()) {
                    val encuestaRequest = EncuestaRequest(
                        calificacion_general = rating,
                        comentarios = comment
                    )
                    encuestaViewModel.crearEncuesta(encuestaRequest)
                    onSubmit(rating, comment)
                }
            }) {
                Text("Enviar")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
    // Mostrar un mensaje de éxito o error basado en el estado de la encuesta
    encuestaState?.let { result ->
        result.onSuccess {
            println("Encuesta enviada con éxito: $it")
        }.onFailure {
            println("Error al enviar encuesta: ${it.message}")
        }
    }
}