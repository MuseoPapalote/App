package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.ButtonDefaults
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papalote.R
// Asegúrate de que el paquete coincida con tu proyecto

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    var username by remember { mutableStateOf("") }
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
            // Texto de Bienvenida
            Text(
                text = "Bienvenid@!",
                color = Color(0xFFC1D72F),
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            // Campo de Usuario
            BasicTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(Color.White)
                    .padding(12.dp),
                decorationBox = { innerTextField ->
                    if (username.isEmpty()) Text("Usuario", color = Color.Gray)
                    innerTextField()
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Campo de Contraseña
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(Color.White)
                    .padding(12.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                decorationBox = { innerTextField ->
                    if (password.isEmpty()) Text("Contraseña", color = Color.Gray)
                    innerTextField()
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Botón de Iniciar Sesión
            Button(
                onClick = { onLoginClick() },
                modifier = Modifier
                    .wrapContentSize(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFC1D72F))
            ) {
                Text(text = "Inicia Sesión", color = Color.White)
            }
        }
    }
}
