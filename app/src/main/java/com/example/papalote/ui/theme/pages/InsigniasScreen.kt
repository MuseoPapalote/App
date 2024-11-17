package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.papalote.R
import com.example.papalote.ui.theme.components.CustomBottomBar
import com.example.papalote.utils.LanguageManager

@Composable
fun InsigniasScreen(navController: NavHostController) {
    // Obtener el idioma actual
    val currentLanguage = LanguageManager.language

    // Textos dependiendo del idioma
    val titleText = if (currentLanguage == "es") "Insignias" else "Badges"
    val badge1 = if (currentLanguage == "es") "Zona Completa" else "Completed Zone"
    val badge2 = if (currentLanguage == "es") "Animales encontrados" else "Animals Found"
    val badge3 = if (currentLanguage == "es") "3 zonas completadas" else "3 Zones Completed"
    val badge4 = if (currentLanguage == "es") "Expreso Completado" else "Express Completed"

    Scaffold(
        bottomBar = {
            CustomBottomBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Top Banner
            TopBanner()
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = titleText,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            // Badges Grid
            BadgesGrid(badge1, badge2, badge3, badge4)

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Calendar and Clock
            BottomIcons()
        }
    }
}

@Composable
fun TopBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFA1D800))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.tocojuegoyaprendo),
            contentDescription = "Logo del Museo",
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun BadgesGrid(badge1: String, badge2: String, badge3: String, badge4: String) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BadgeItem(badge1)
            BadgeItem(badge2)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BadgeItem(badge3)
            BadgeItem(badge4)
        }
    }
}

@Composable
fun BadgeItem(label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.medalla),
            contentDescription = label,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}

@Composable
fun BottomIcons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFA1D800))
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.medalla),
            contentDescription = "Calendar Icon",
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.medalla),
            contentDescription = "Clock Icon",
            modifier = Modifier.size(40.dp)
        )
    }
}