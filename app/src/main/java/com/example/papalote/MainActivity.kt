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
import com.example.papalote.ui.theme.pages.*
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
    // Cambiamos la pantalla de inicio a "splash"
    NavHost(navController = navController, startDestination = "splash") {

        // Pantalla de Splash
        composable("splash") {
            SplashScreen(navController = navController)
        }

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
                onLoginSuccess = { navController.navigate("zones") },
                onBack = { navController.navigateUp() }
            )
        }

        // Pantalla de registro
        composable("register") {
            RegisterScreen(
                onBack = { navController.navigateUp() },
                onRegistrationSuccess = {navController.navigate("welcome")}
                )
        }

        // Pantalla de Zonas
        composable("zones") {
            ZonasScreen(
                navController = navController,
                onZoneClick = { zoneName ->
                    navController.navigate("zoneDetail/$zoneName")
                },
                onBack = { navController.navigateUp() }
            )
        }

        // Pantalla de Detalle de Zona
        composable("zoneDetail/{zoneName}") { backStackEntry ->
            val zoneName = backStackEntry.arguments?.getString("zoneName") ?: "Zona Desconocida"
            ZoneDetailScreen(
                zoneName = zoneName,
                onBack = { navController.navigateUp() },
                navController = navController,
                onMedalClick = { navController.navigate("insignias") }
            )
        }

        // Pantalla de Insignias
        composable("insignias") {
            InsigniasScreen(navController = navController)
        }

        // Pantalla de Escaneo de QR
        composable("scanQR") {
            EscaneoQRScreen(navController = navController)
        }

        // Pantalla de Dinosaurio (ComunicoScreen)
        composable("dinosaur/{zoneName}") { backStackEntry ->
            val zoneName = backStackEntry.arguments?.getString("zoneName") ?: "Zona Desconocida"
            ComunicoScreen(
                zoneName = zoneName,
                onBack = { navController.navigateUp() },
                navController = navController
            )
        }
    }
}