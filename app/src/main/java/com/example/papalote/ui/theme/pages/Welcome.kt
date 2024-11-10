package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.papalote.R
import com.example.papalote.ui.theme.PapaloteTheme

@Composable
fun WelcomeScreen(onLoginClicked: () -> Unit, onRegisterClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFD8E56D)), // Fondo verde claro
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.papalotelogoo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 50.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Botón de Iniciar Sesión
            GradientButton(
                text = "Iniciar Sesión",
                onClick = onLoginClicked
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botón de Registrarse
            GradientButton(
                text = "Registrarse",
                onClick = onRegisterClicked
            )
        }
    }
}

@Composable
fun GradientButton(text: String, onClick: () -> Unit) {
    androidx.compose.material.Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(48.dp),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFA4CD39), Color(0xFFC1D72F))
                    ),
                    shape = CircleShape
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp
            )
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