package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.papalote.R
import com.example.papalote.states.UserState
import com.example.papalote.viewModel.UserViewModel

@Composable
fun UserProfileScreen(
    viewModel: UserViewModel = viewModel(),
    navController: NavHostController
) {
    val userState by viewModel.userState.collectAsState()

    // Solo llamamos a `fetchUserInfo` si estamos en el estado Idle
    LaunchedEffect(userState) {
        if (userState is UserState.Idle) {
            println("Llamando a fetchUserInfo desde UserProfileScreen...")
            viewModel.fetchUserInfo()
        }
    }

    when (userState) {
        is UserState.Loading -> {
            println("Estado actual: Loading")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFD6E800)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Cargando...", fontSize = 20.sp, color = Color.White)
            }
        }
        is UserState.Success -> {
            println("Estado actual: Success")
            val user = (userState as UserState.Success).user
            println("Mostrando datos del usuario: $user")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFD6E800))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderWithLogoo()
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.White, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.userscc),
                        contentDescription = "Usuario",
                        modifier = Modifier.size(64.dp),
                        tint = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = user.nombre,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                UserInfoItem(icon = R.drawable.userscc, label = user.nombre)
                UserInfoItem(icon = R.drawable.birthday_icon, label = user.fecha_nacimiento?.toString() ?: "" )
                UserInfoItem(icon = R.drawable.lock_icon, label = user.email, editable = false)
                Spacer(modifier = Modifier.height(16.dp))
                InsigniasInfo(total = user.visitas.size)

                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        // Lógica de logout
                        viewModel.logoutUser()
                        // Redirigimos a la pantalla de Splash (o Login)
                        navController.navigate("splash") {
                            popUpTo("profile") { inclusive = true } // Eliminar la pantalla de perfil del back stack
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Cerrar sesión", fontSize = 18.sp, color = Color.White)
                }
            }
        }
        is UserState.Error -> {
            val errorMessage = (userState as UserState.Error).message
            println("Mostrando error: $errorMessage")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFD6E800)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: $errorMessage", fontSize = 20.sp, color = Color.Red)
            }
        }
        is UserState.Idle -> {
            println("Estado actual: Idle")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFD6E800)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Esperando datos...", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}


@Composable
fun HeaderWithLogoo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFC2D401))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tocojuegoyaprendo),
            contentDescription = "Logo del Museo",
            modifier = Modifier.width(370.dp)
        )
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
                painter = painterResource(id = R.drawable.editar), // Ícono de edición
                contentDescription = "Editar",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun InsigniasInfo(total: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.qrs), // Cambia al ícono de medalla
            contentDescription = "Medalla",
            tint = Color(0xFFFFC107),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Número de escaneos realizados: $total",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
    }
}
