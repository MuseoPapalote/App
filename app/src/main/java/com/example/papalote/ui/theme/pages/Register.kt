package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papalote.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.papalote.utils.LanguageManager

@Composable
fun RegisterScreen(onBack: () -> Unit) {
    var fullName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Obtener el idioma actual
    val currentLanguage = LanguageManager.language

    // Textos dinámicos basados en el idioma
    val registerTitle = if (currentLanguage == "es") "REGISTRARME" else "REGISTER"
    val fullNamePlaceholder = if (currentLanguage == "es") "Nombre completo" else "Full Name"
    val birthDatePlaceholder = if (currentLanguage == "es") "Fecha de nacimiento" else "Birth Date"
    val usernamePlaceholder = if (currentLanguage == "es") "Usuario" else "Username"
    val passwordPlaceholder = if (currentLanguage == "es") "Contraseña" else "Password"
    val registerButtonText = if (currentLanguage == "es") "REGISTRARME" else "REGISTER"
    val backButtonText = if (currentLanguage == "es") "Volver" else "Back"
    val socialRegisterText = if (currentLanguage == "es") "O regístrate con" else "Or register with"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD8E56D)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 16.dp)
        ) {
            // Parte superior: Logo y título
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.papalotelogoo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(bottom = 20.dp)
                )

                Text(
                    text = registerTitle,
                    color = Color(0xFF707070),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            // Campos de entrada
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                InputFieldWithIcon(
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = fullNamePlaceholder,
                    icon = R.drawable.user_icon
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputFieldWithIcon(
                    value = birthDate,
                    onValueChange = { birthDate = it },
                    placeholder = birthDatePlaceholder,
                    icon = R.drawable.birthday_icon
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputFieldWithIcon(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = usernamePlaceholder,
                    icon = R.drawable.user_icon
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputFieldWithIcon(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = passwordPlaceholder,
                    icon = R.drawable.lock_icon,
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onPasswordToggle = { passwordVisible = !passwordVisible }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de registro
            Button(
                onClick = { /* Lógica de registro */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth()
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
                        text = registerButtonText,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de volver
            Button(
                onClick = { onBack() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(bottom = 20.dp)
            ) {
                Text(text = backButtonText, color = Color.White)
            }

            // Texto de registro social
            Text(
                text = socialRegisterText,
                color = Color(0xFF707070),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            // Iconos de redes sociales
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebooklogo),
                    contentDescription = "Facebook",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 20.dp),
                    contentScale = ContentScale.Fit
                )
                Image(
                    painter = painterResource(id = R.drawable.googlelogo),
                    contentDescription = "Google",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 20.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
fun InputFieldWithIcon(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: Int,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordToggle: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .background(Color.White, shape = CircleShape)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = placeholder,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            decorationBox = { innerTextField ->
                if (value.isEmpty()) Text(placeholder, color = Color(0xFFCCCCCC))
                innerTextField()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(onBack = {})
}