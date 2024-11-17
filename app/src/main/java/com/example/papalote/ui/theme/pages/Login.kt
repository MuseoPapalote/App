// LoginScreen.kt
package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.papalote.R
import com.example.papalote.utils.LanguageManager
import androidx.compose.ui.tooling.preview.Preview
import com.example.papalote.states.LoginState
import com.example.papalote.viewModel.LoginViewModel
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    onBack: () -> Unit
) {
    fun hashPassword(password: String): String {
        val bytes = password.toByteArray(UTF_8)
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hashedPass by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showLoadingDialog by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Observa el estado del login
    val loginState by viewModel.loginState.collectAsState()

    fun isValidEmail(email: String): Boolean{
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return email.matches(emailRegex.toRegex())
    }

    fun validateFields(): Boolean{
        return when{
            email.isBlank() -> {
                errorMessage = "El campo de correo está vacío"
                false
            }
            !isValidEmail(email) -> {
                errorMessage = "El correo no es válido"
                false
            }
            password.isBlank() -> {
                errorMessage = "El campo de contraseña está vacío"
                false
            }
            else -> true
        }
    }

    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginState.Loading -> {
                showLoadingDialog = true
            }
            is LoginState.Success -> {
                showLoadingDialog = false
                onLoginSuccess()
            }
            is LoginState.Error -> {
                showError = true
                errorMessage = (loginState as LoginState.Error).message
            }
            else -> {
                showLoadingDialog = false
                showError = false
            }
        }
    }

    // Texto para los elementos de la pantalla según el idioma
    val loginText = if (LanguageManager.language == "es") "INICIAR SESIÓN" else "LOGIN"
    val usernamePlaceholder = if (LanguageManager.language == "es") "USUARIO" else "USERNAME"
    val passwordPlaceholder = if (LanguageManager.language == "es") "CONTRASEÑA" else "PASSWORD"
    val enterText = if (LanguageManager.language == "es") "ENTRAR" else "ENTER"
    val forgotPasswordText = if (LanguageManager.language == "es") "¿Olvidaste tu contraseña?" else "Forgot your password?"
    val registerText = if (LanguageManager.language == "es") "Registrarme" else "Register"
    val backText = if (LanguageManager.language == "es") "Volver" else "Back"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD8E56D)), //Fondo verde claro
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 16.dp)
        ) {
            //Parte superior: Logo y título
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.papalotelogoo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(bottom = 20.dp)
                )
                Text(
                    text = loginText,
                    color = Color(0xFF707070),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            //Campos de usuario y contraseña
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                InputFieldWithIcon(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "CORREO",
                    icon = R.drawable.user_icon
                )
                Spacer(modifier = Modifier.height(16.dp))
                InputFieldWithIcon(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "CONTRASEÑA",
                    icon = R.drawable.lock_icon,
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onPasswordToggle = { passwordVisible = !passwordVisible }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (validateFields()){
                            hashedPass = hashPassword(password)
                            viewModel.loginUser(email, hashedPass)

                            showError = false
                        } else{
                            showError = true
                        }
                              },
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
                            text = enterText,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }

                if (showError) {
                    Text(text = errorMessage, color = Color.Red, fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Botón de "Volver"
                Button(
                    onClick = { onBack() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(text = backText, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = forgotPasswordText,
                    color = Color(0xFF707070),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Text(
                    text = registerText,
                    color = Color(0xFF707070),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Parte inferior: Banderas de idioma
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    //LoginScreen(onLoginClick = {}, onBack = {})
}