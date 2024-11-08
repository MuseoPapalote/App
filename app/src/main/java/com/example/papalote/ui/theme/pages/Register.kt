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
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.papalote.RegistrationViewModel
import com.example.papalote.RegistrationState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Calendar
import android.app.DatePickerDialog
import androidx.compose.ui.platform.LocalContext



fun hashPassword(password: String): String {
    val bytes = password.toByteArray(UTF_8)
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}

@Composable
fun RegisterScreen(
    viewModel: RegistrationViewModel = viewModel(),
    onBack: () -> Unit,
    onRegistrationSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf(Date()) }

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hashedPass by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val registrationState by viewModel.registrationState.collectAsState()
    var showLoadingDialog by remember { mutableStateOf(false) }
    var showHashDialog by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current // Obtén el contexto aquí
    fun showDatePicker(onDateSelected: (Date) -> Unit) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }.time
                onDateSelected(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return email.matches(emailRegex.toRegex())
    }

    // Validation function updated for email
    fun validateFields(): Boolean {
        return when {
            email.isBlank() -> {
                errorMessage = "Por favor ingrese su correo electrónico"
                false
            }
            !isValidEmail(email) -> {
                errorMessage = "Por favor ingrese un correo electrónico válido"
                false
            }
            birthDate == null -> {
                errorMessage = "Por favor ingrese su fecha de nacimiento"
                false
            }
            username.isBlank() -> {
                errorMessage = "Por favor ingrese un nombre de usuario"
                false
            }
            password.isBlank() -> {
                errorMessage = "Por favor ingrese una contraseña"
                false
            }
            password.length < 8 -> {
                errorMessage = "La contraseña debe tener al menos 8 caracteres"
                false
            }
            else -> true
        }
    }

    LaunchedEffect(registrationState) {
        when (registrationState) {
            is RegistrationState.Loading -> {
                showLoadingDialog = true
            }
            is RegistrationState.Success -> {
                showLoadingDialog = false
                onRegistrationSuccess()
            }
            is RegistrationState.Error -> {
                showLoadingDialog = false
                showError = true
                errorMessage = (registrationState as RegistrationState.Error).message
            }
            else -> {
                showLoadingDialog = false
            }
        }
    }


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
            // Logo and title
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
                    text = "REGISTRARME",
                    color = Color(0xFF707070),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            // Input fields
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                InputFieldWithIcon(
                    value=email,
                    onValueChange={
                        email = it
                        showError = false
                    },
                    placeholder = "Correo electrónico",
                    icon = R.drawable.user_icon
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    showDatePicker { selectedDate ->
                        birthDate = selectedDate
                        showError = false
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = CircleShape)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = if (birthDate != null) {
                            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(birthDate!!)
                        } else {
                            "Fecha de nacimiento"
                        },
                        color = if (birthDate != null) Color.Black else Color(0xFFFFFFFF) // Cambia el color según la selección
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                InputFieldWithIcon(
                    value = username,
                    onValueChange = {
                        username = it
                        showError = false
                    },
                    placeholder = "Usuario",
                    icon = R.drawable.user_icon
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputFieldWithIcon(
                    value = password,
                    onValueChange = {
                        password = it
                        showError = false
                    },
                    placeholder = "Contraseña",
                    icon = R.drawable.lock_icon,
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onPasswordToggle = { passwordVisible = !passwordVisible }
                )

                if (showError) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Register button
            Button(
                onClick = {
                    if (validateFields()) {
                        hashedPass=hashPassword(password)
                        viewModel.registerUser(email, birthDate, username, hashedPass)

                        showHashDialog = true
                        showError = false
                    } else {
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
                        text = "REGISTRARME",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            if (showHashDialog) {
                AlertDialog(
                    onDismissRequest = { showHashDialog = false },
                    title = { Text("Hash de la contraseña") },
                    text = {
                        Column {
                            Text("Contraseña original: $password")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Hash SHA-256: $hashedPass")
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = { showHashDialog = false }) {
                            Text("Cerrar")
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Back button
            Button(
                onClick = { onBack() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(bottom = 20.dp)
            ) {
                Text(text = "Volver", color = Color.White)
            }

            // Social login section
            Text(
                text = "O regístrate con",
                color = Color(0xFF707070),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )

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

            // Language flags
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mexico_flag),
                    contentDescription = "Bandera de México",
                    modifier = Modifier.size(48.dp).padding(end = 16.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.usa_flag),
                    contentDescription = "Bandera de EE. UU.",
                    modifier = Modifier.size(48.dp).padding(start = 16.dp)
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
