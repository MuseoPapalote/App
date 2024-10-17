package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.ButtonDefaults
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papalote.R // Asegúrate de que el paquete coincida con tu proyecto

@Composable
fun RegisterScreen(onBack: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

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
            // Título de bienvenida
            Text(
                text = "Bienvenid@!",
                color = Color(0xFFC1D72F),
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            // Campo de correo electrónico
            BasicTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(Color.White)
                    .padding(12.dp), // Emula el estilo del XML
                decorationBox = { innerTextField ->
                    if (email.isEmpty()) Text("Correo", color = Color.Gray)
                    innerTextField()
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Campo de contraseña
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(Color.White)
                    .padding(12.dp), // Emula el estilo del XML
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                decorationBox = { innerTextField ->
                    if (password.isEmpty()) Text("Contraseña", color = Color.Gray)
                    innerTextField()
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Botón de registrarse
            Button(
                onClick = { /* Lógica de registro */ },
                modifier = Modifier
                    .wrapContentSize(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFC1D72F))
            ) {
                Text(text = "Registrarse", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Texto de opciones de redes sociales
            Text(
                text = "Ó Regístrate con:",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Íconos de redes sociales
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Google icon
                Image(
                    painter = painterResource(id = R.drawable.googlelogo),
                    contentDescription = "Google Logo",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 20.dp),
                    contentScale = ContentScale.Fit
                )

                // Facebook icon
                Image(
                    painter = painterResource(id = R.drawable.facebooklogo),
                    contentDescription = "Facebook Logo",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(start = 20.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}
