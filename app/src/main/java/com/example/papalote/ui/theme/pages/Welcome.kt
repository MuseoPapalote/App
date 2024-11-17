package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.papalote.utils.LanguageManager

@Composable
fun WelcomeScreen(onLoginClicked: () -> Unit, onRegisterClicked: () -> Unit) {
    // Definir los textos según el idioma seleccionado
    val loginText = if (LanguageManager.language == "es") "Iniciar Sesión" else "Log In"
    val registerText = if (LanguageManager.language == "es") "Registrarse" else "Register"

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
                text = loginText,
                onClick = onLoginClicked
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botón de Registrarse
            GradientButton(
                text = registerText,
                onClick = onRegisterClicked
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Banderas de idioma
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mexico_flag),
                    contentDescription = "Español",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 16.dp)
                        .clickable {
                            LanguageManager.changeLanguage("es")
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.usa_flag),
                    contentDescription = "Inglés",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(start = 16.dp)
                        .clickable {
                            LanguageManager.changeLanguage("en")
                        }
                )
            }
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