package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.papalote.R

@Composable
fun MapaScreen(modifier: Modifier = Modifier) {
    // Áreas interactivas del mapa
    val clickableAreas = listOf(
        ClickableArea(x = 0.2f, y = 0.3f, width = 0.05f, height = 0.05f, label = "Exhibición A"),
        ClickableArea(x = 0.5f, y = 0.5f, width = 0.05f, height = 0.05f, label = "Exhibición B"),
        ClickableArea(x = 0.8f, y = 0.7f, width = 0.05f, height = 0.05f, label = "Exhibición C")
    )

    // Estados para zoom y pan
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var selectedLabel by remember { mutableStateOf<String?>(null) }

    // Tamaño de la imagen
    var imageSize by remember { mutableStateOf(IntSize.Zero) }

    val density = LocalDensity.current

    Box(modifier = modifier.fillMaxSize()) {
        // Imagen interactiva con zoom y pan
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale = (scale * zoom).coerceIn(1f, 5f) // Limita el zoom entre 1x y 5x
                        offset += pan
                    }
                }
        ) {
            Box(
                modifier = Modifier.graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
            ) {
                // Imagen del mapa
                Image(
                    painter = painterResource(id = R.drawable.mapapapalote), // Usa tu imagen del mapa
                    contentDescription = "Mapa interactivo",
                    modifier = Modifier
                        .fillMaxSize()
                        .onGloballyPositioned { coordinates ->
                            imageSize = coordinates.size
                        }
                )

                // Áreas clicables
                if (imageSize.width > 0 && imageSize.height > 0) {
                    clickableAreas.forEach { area ->
                        val areaX = area.x * imageSize.width
                        val areaY = area.y * imageSize.height
                        val areaWidth = area.width * imageSize.width
                        val areaHeight = area.height * imageSize.height

                        Box(
                            modifier = Modifier
                                .offset(
                                    x = with(density) { areaX.toDp() },
                                    y = with(density) { areaY.toDp() }
                                )
                                .size(with(density) { areaWidth.toDp() }, with(density) { areaHeight.toDp() })
                                .pointerInput(Unit) {
                                    detectTapGestures {
                                        selectedLabel = area.label
                                    }
                                }
                        )
                    }
                }
            }
        }

        // Mensaje al seleccionar un área
        selectedLabel?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Seleccionado: $it",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// Clase para definir las áreas clicables
data class ClickableArea(
    val x: Float, // Posición relativa x (0f - 1f)
    val y: Float, // Posición relativa y (0f - 1f)
    val width: Float, // Ancho relativo (0f - 1f)
    val height: Float, // Altura relativa (0f - 1f)
    val label: String // Etiqueta del área
)