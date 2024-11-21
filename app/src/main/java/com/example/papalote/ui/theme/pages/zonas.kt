package com.example.papalote.ui.theme.pages

import android.widget.GridLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.papalote.R
import com.example.papalote.ui.theme.components.CustomBottomBar
import com.example.papalote.utils.LanguageManager
import com.example.papalote.utils.TokenManager

@Composable
fun ZonasScreen(navController: NavHostController,tokenManager: TokenManager, onZoneClick: (String) -> Unit, onBack: () -> Unit) {
    // Definir los textos dinámicamente según el idioma
    val titleText = if (LanguageManager.language == "es") "Zonas" else "Zones"
    val volverText = if (LanguageManager.language == "es") "Volver" else "Back"
    val gridItems = if (LanguageManager.language == "es") {
        listOf(
            Pair("Comprendo", R.drawable.comprendo),
            Pair("Comunico", R.drawable.comunico),
            Pair("Expreso", R.drawable.expreso),
            Pair("Pequeños", R.drawable.pequenos),
            Pair("Pertenezco", R.drawable.pertenezco),
            Pair("Soy", R.drawable.soy)
        )
    } else {
        listOf(
            Pair("Understand", R.drawable.comprendo),
            Pair("Communicate", R.drawable.comunico),
            Pair("Express", R.drawable.expreso),
            Pair("Little Ones", R.drawable.pequenos),
            Pair("Belong", R.drawable.pertenezco),
            Pair("I Am", R.drawable.soy)
        )
    }

    Scaffold(
        bottomBar = {
            // Usamos CustomBottomBar aquí
            CustomBottomBar(
                navController = navController,
                tokenManager = tokenManager
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD8E56D))
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = titleText,
                    color = Color(0xFF707070),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Iconos y Etiquetas GRID
                GridLayout(
                    items = gridItems,
                    onZoneClick = onZoneClick
                )

                Button(
                    onClick = { onBack() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(text = volverText, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun GridLayout(items: List<Pair<String, Int>>, onZoneClick: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Número de columnas
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items) { item ->
            ZoneItem(item.first, item.second) {
                onZoneClick(item.first)
            }
        }
    }
}

@Composable
fun ZoneItem(name: String, icon: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
            .border(1.dp, Color.Gray) // Ejemplo de borde
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = icon), contentDescription = null)
        Text(text = name)
    }
}