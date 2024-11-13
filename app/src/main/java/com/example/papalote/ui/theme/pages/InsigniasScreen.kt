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

@Composable
fun InsigniasScreen(navController: NavHostController) {
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
                text = "Insignias",
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            // Badges Grid
            BadgesGrid()

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
fun BadgesGrid() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BadgeItem("Zona Completa")
            BadgeItem("Animales encontrados")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BadgeItem("3 zonas completadas")
            BadgeItem("Expreso Completado")
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