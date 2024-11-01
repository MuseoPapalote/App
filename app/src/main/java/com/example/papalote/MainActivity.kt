package com.example.papalote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.papalote.ui.theme.PapaloteTheme
import com.example.papalote.ui.theme.pages.WelcomeScreen
import com.example.papalote.ui.theme.pages.LoginScreen
import com.example.papalote.ui.theme.pages.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PapaloteTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        // Pantalla de bienvenida
        composable("welcome") {
            WelcomeScreen(
                onLoginClicked = { navController.navigate("login") },
                onRegisterClicked = { navController.navigate("register") }
            )
        }
        // Pantalla de inicio de sesión
        composable("login") {
            LoginScreen(
                onLoginClick = {
                    // Aquí puedes definir la acción para iniciar sesión.
                    // Por ahora, puede estar vacío o puedes navegar a otra pantalla si es necesario
                },
                onBack = { navController.navigateUp() } // Navega hacia atrás
            )
        }
        // Pantalla de registro
        composable("register") {
            RegisterScreen(
                onBack = { navController.navigateUp() } // Navega hacia atrás
            )
        }
    }
}
