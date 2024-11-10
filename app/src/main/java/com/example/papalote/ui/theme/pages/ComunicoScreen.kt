package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papalote.R
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ComunicoScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)), // Fondo claro
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Encabezado
        Text(
            text = "comunico",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF016ED2), // Color azul del título
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Imagen del dinosaurio
        Image(
            painter = painterResource(id = R.drawable.dinosaur_image), // Reemplaza con tu imagen
            contentDescription = "Dinosaurio",
            modifier = Modifier
                .size(250.dp)
                .padding(16.dp)
                .background(Color(0xFF4CA6A8), shape = RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

        // Texto de la pregunta
        Text(
            text = "Nombre de este dinosaurio",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .background(Color(0xFF4CA6A8), shape = RoundedCornerShape(12.dp))
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones de respuesta
        AnswerButton(text = "Mosasaurio")
        Spacer(modifier = Modifier.height(8.dp))
        AnswerButton(text = "Megalodón")
        Spacer(modifier = Modifier.height(8.dp))
        AnswerButton(text = "Tiranosaurio Rex")

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de volver
        Button(
            onClick = { onBack() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text = "Volver", color = Color.White)
        }
    }
}

@Composable
fun AnswerButton(text: String) {
    Button(
        onClick = { /* Acción del botón */ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD0D0D0)), // Color gris claro
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(50.dp),
        shape = CircleShape
    ) {
        Text(
            text = text,
            color = Color(0xFF4CA6A8), // Color azul del texto
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComunicoScreenPreview() {
    ComunicoScreen(onBack = {})
}