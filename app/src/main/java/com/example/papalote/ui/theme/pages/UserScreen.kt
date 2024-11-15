package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papalote.R

@Composable
fun UserProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD6E800)) // Fondo verde similar
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "toco juego y aprendo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        // Imagen de usuario
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.White, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_user), // Cambia por el ícono adecuado
                contentDescription = "Usuario",
                modifier = Modifier.size(64.dp),
                tint = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre del usuario
        Text(
            text = "USUARIO",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Información del usuario
        UserInfoItem(icon = R.drawable.ic_user, label = "Héctor de Jesús Taméz Treviño")
        UserInfoItem(icon = R.drawable.ic_calendar, label = "29 de febrero 2008")
        UserInfoItem(icon = R.drawable.ic_email, label = "xXHectorXx@hotmail.com", editable = true)
        UserInfoItem(icon = R.drawable.ic_lock, label = "************", editable = true)

        Spacer(modifier = Modifier.height(16.dp))

        // Número de insignias alcanzadas
        InsigniasInfo()
    }
}

@Composable
fun UserInfoItem(icon: Int, label: String, editable: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xFFA1D800),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        if (editable) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit), // Ícono de edición
                contentDescription = "Editar",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun InsigniasInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_medal), // Cambia al ícono de medalla
            contentDescription = "Medalla",
            tint = Color(0xFFFFC107),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Número de insignias alcanzadas: 0",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
    }
}