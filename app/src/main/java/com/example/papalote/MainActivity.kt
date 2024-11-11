package com.example.papalote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.papalote.ui.theme.pages.ComunicoScreen
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.papalote.ui.theme.PapaloteTheme
import com.example.papalote.ui.theme.pages.WelcomeScreen
import com.example.papalote.ui.theme.pages.LoginScreen
import com.example.papalote.ui.theme.pages.RegisterScreen
import com.example.papalote.ui.theme.pages.ZonasScreen
import com.example.papalote.ui.theme.pages.ZoneDetailScreen
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
        composable("welcome") {
            WelcomeScreen(
                onLoginClicked = { navController.navigate("login") },
                onRegisterClicked = { navController.navigate("register") }
            )
        }
        composable("login") {
            LoginScreen(
                onLoginClick = { navController.navigate("zones") },
                onBack = { navController.navigateUp() }
            )
        }
        composable("register") {
            RegisterScreen(onBack = { navController.navigateUp() })
        }
        composable("zones") {
            ZonasScreen(
                onZoneClick = { zoneName -> navController.navigate("zoneDetail/$zoneName") },
                onBack = { navController.navigateUp() }
            )
        }
        composable("zoneDetail/{zoneName}") { backStackEntry ->
            val zoneName = backStackEntry.arguments?.getString("zoneName") ?: "Zona Desconocida"
            ZoneDetailScreen(
                zoneName = zoneName,
                onBack = { navController.navigateUp() },
                navController = navController
            )
        }
        // Pantalla del dinosaurio
        composable("dinosaur/{zoneName}") { backStackEntry ->
            val zoneName = backStackEntry.arguments?.getString("zoneName") ?: "Zona Desconocida"
            ComunicoScreen(zoneName = zoneName, onBack = { navController.navigateUp() })
        }
    }
}
