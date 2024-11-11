package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papalote.R

@Preview
@Composable
fun InsigniasScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8A3EB3))
            .padding(16.dp)
    ) {
        // Top Banner
        TopBanner()
        Spacer(modifier = Modifier.height(24.dp))

        // Badges Grid with a Single Icon for All
        BadgesGrid()

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Calendar and Clock
        BottomIcons()
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
        Text(
            text = "Insignias",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.medalla),
            contentDescription = "Menu Icon",
            modifier = Modifier.size(24.dp)
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
            color = Color.White
        )
    }
}

@Composable
fun BottomIcons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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