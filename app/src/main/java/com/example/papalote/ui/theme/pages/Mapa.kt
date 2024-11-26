package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.papalote.R

@Composable
fun MapaScreen(navController: NavHostController, onBack: () -> Unit) {
    // Estados para zoom y pan
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var imageSize by remember { mutableStateOf(IntSize.Zero) }

    val density = LocalDensity.current

    // Definición de las zonas interactivas
    val zonas = listOf(
        "comprendo" to Rect(0.10f, 0.10f, 0.25f, 0.25f),
        "comunico" to Rect(0.10f, 0.30f, 0.25f, 0.45f),
        "expreso" to Rect(0.30f, 0.15f, 0.50f, 0.35f),
        "pequenos" to Rect(0.30f, 0.40f, 0.50f, 0.55f),
        "pertenezco" to Rect(0.55f, 0.40f, 0.75f, 0.55f),
        "soy" to Rect(0.60f, 0.60f, 0.80f, 0.75f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6F4F1)), // Fondo claro
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            // Encabezado estilizado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF4CAF50), Color(0xFF81C784)) // Degradado verde
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Mapa Interactivo del Museo",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // Contenedor del mapa
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale = (scale * zoom).coerceIn(1f, 5f) // Zoom entre 1x y 5x
                            offset += pan
                        }
                    }
                    .pointerInput(Unit) {
                        detectTapGestures { tapOffset ->
                            // Conversión del clic a coordenadas relativas
                            val relativeX = (tapOffset.x - offset.x) / (imageSize.width * scale)
                            val relativeY = (tapOffset.y - offset.y) / (imageSize.height * scale)

                            // Navegación a la zona correspondiente
                            zonas.forEach { (id, rect) ->
                                if (relativeX in rect.left..rect.right && relativeY in rect.top..rect.bottom) {
                                    // Navegar a la ruta específica según el id de la zona
                                    when (id) {
                                        "comprendo" -> navController.navigate("comprendo")
                                        "comunico" -> navController.navigate("comunico")
                                        "expreso" -> navController.navigate("expreso")
                                        "pequenos" -> navController.navigate("pequenos")
                                        "pertenezco" -> navController.navigate("pertenezco")
                                        "soy" -> navController.navigate("soy")
                                    }
                                }
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                // Imagen del mapa con transformación de zoom y pan
                Image(
                    painter = painterResource(id = R.drawable.mapapapalote),
                    contentDescription = "Mapa interactivo del museo",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight()
                        .onGloballyPositioned { coordinates ->
                            imageSize = coordinates.size
                        }
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offset.x,
                            translationY = offset.y
                        )
                )
            }

            // Botón de regreso
            Button(
                onClick = onBack,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
            ) {
                Text(
                    text = "Volver",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
