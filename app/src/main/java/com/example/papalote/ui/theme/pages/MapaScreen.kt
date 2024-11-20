package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import com.example.papalote.R

@Composable
fun MapaScreen(onBack: () -> Unit) {
    var scale by remember { mutableStateOf(1f) } // Nivel de zoom
    var offsetX by remember { mutableStateOf(0f) } // Desplazamiento horizontal
    var offsetY by remember { mutableStateOf(0f) } // Desplazamiento vertical
    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    var imageSize by remember { mutableStateOf(IntSize.Zero) }

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
            // Encabezado con degradado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF4CAF50), Color(0xFF81C784))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Mapa del Museo",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // Contenedor para el mapa con desplazamiento restringido
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
                    .onGloballyPositioned { coordinates ->
                        containerSize = coordinates.size // Tamaño del contenedor
                    }
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale = (scale * zoom).coerceIn(1f, 5f) // Zoom entre 1x y 5x

                            // Calcula los límites de desplazamiento para evitar que la imagen se salga
                            val maxX = (imageSize.width * scale - containerSize.width) / 2
                            val maxY = (imageSize.height * scale - containerSize.height) / 2

                            offsetX = (offsetX + pan.x).coerceIn(-maxX, maxX)
                            offsetY = (offsetY + pan.y).coerceIn(-maxY, maxY)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mapapapalote), // Asegúrate de que este recurso exista
                    contentDescription = "Mapa del Museo",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight() // Ajusta la imagen a la altura máxima del contenedor
                        .onGloballyPositioned { coordinates ->
                            imageSize = coordinates.size // Tamaño de la imagen
                        }
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offsetX,
                            translationY = offsetY
                        )
                )
            }

            // Botón para regresar
            Button(
                onClick = onBack,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp)
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

@Preview(showBackground = true)
@Composable
fun MapaScreenPreview() {
    MapaScreen(onBack = {}) // Lambda vacío para la vista previa
}