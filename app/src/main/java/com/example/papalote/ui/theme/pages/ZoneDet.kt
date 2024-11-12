package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.clickable
import androidx.compose.ui.unit.sp
import com.example.papalote.R


@Composable
fun ZoneDetailScreen(zoneName: String, onBack: () -> Unit, navController: NavHostController, onMedalClick: () -> Unit) {
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
            HeaderWithLogo()
            // Barra de encabezado
            Header(zoneName = zoneName)

            // Sección de actividades
            ActivitiesSection(navController = navController, zoneName = zoneName)

            // Sección de medallas
            MedalSection(zoneName = zoneName, onMedalClick = onMedalClick)

            // Barra de navegación inferior
            BottomNavigationBar(onBack = onBack)
        }
    }
}

@Composable
fun HeaderWithLogo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFC2D401)) // Color de fondo del nuevo encabezado
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tocojuegoyaprendo), // Cambia a tu recurso de imagen de logo
            contentDescription = "Logo del Museo",
            modifier = Modifier.width(2700.dp) // Ajusta el tamaño según sea necesario
        )
    }
}

@Composable
fun Header(zoneName: String) {
    // Selección del color del texto según el nombre de la zona
    val titleColor = when (zoneName) {
        "Comunico" -> Color(0xFF016ED2)
        "Pertenezco" -> Color(0xFFC2D401)
        "Expreso" -> Color(0xFFFF6C01)
        "Soy" -> Color(0xFFDD0633)
        "Comprendo" -> Color(0xFF7C55C7)
        "Pequeños" -> Color(0xFF008C95)
        else -> Color.Black // Color por defecto si la zona no coincide
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp), // Agrega un margen superior para separar el título del logo
            //.background(Color(0xFFE0E600)), // Color de fondo de encabezado
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = zoneName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = titleColor, // Aplica el color del título aquí
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "Actividades del día",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun ActivitiesSection(navController: NavHostController, zoneName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // Botón Actividad 1
        ActivityCard(
            color = Color(0xFFE6A957),
            icon = painterResource(id = R.drawable.calendario),
            text = "Actividad 1",
            onClick = { navController.navigate("dinosaur/$zoneName") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ActivityCard(
            color = Color(0xFF6AB98D),
            icon = painterResource(id = R.drawable.calendario),
            text = "Actividad 2",
            onClick = { navController.navigate("dinosaur/$zoneName") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ActivityCard(
            color = Color(0xFF55A8E2),
            icon = painterResource(id = R.drawable.calendario),
            text = "Actividad 3",
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
        Icon(
            icon,
            contentDescription = null,
            tint = Color.White,
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
        else -> R.drawable.soylogo // Imagen por defecto si la zona no coincide
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(Color(0x80000000))  //Efecto fondo semi transparente/negro
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Contenido de medallas encima de la imagen de fondo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(4) { rowIndex ->
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
                                .clickable { onMedalClick() }  // Agrega onClick a la medalla
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
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
