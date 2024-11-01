package com.example.papalote.ui.theme.pages // Asegúrate de que el paquete sea correcto

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.papalote.R // Asegúrate de que el recurso esté disponible
import com.example.papalote.ui.theme.PapaloteTheme

@Composable
fun WelcomeScreen(onLoginClicked: () -> Unit, onRegisterClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFF8E24AA)), // Fondo de color hexadecimal
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.papalotelogoo), // Asegúrate de que papalotelogo existe en res/drawable
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 50.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Botón de Iniciar Sesión
            Button(
                onClick = onLoginClicked,
                modifier = Modifier.wrapContentSize()
            ) {
                Text(text = "Iniciar Sesión", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón de Registrarse
            Button(
                onClick = onRegisterClicked,
                modifier = Modifier.wrapContentSize()
            ) {
                Text(text = "Registrarse", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWelcomeScreen() {
    PapaloteTheme {
        WelcomeScreen(onLoginClicked = {}, onRegisterClicked = {})
    }
}
