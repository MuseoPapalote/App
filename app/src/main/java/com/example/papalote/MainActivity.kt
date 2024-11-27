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
import com.example.papalote.utils.TokenManager
import com.example.papalote.viewModel.UserViewModel
import com.example.papalote.viewModel.LoginViewModel
import com.example.papalote.viewModel.RegistrationViewModel
import com.example.papalote.viewModelFactory.RegistrationViewModelFactory
import com.example.papalote.viewModelFactory.LoginViewModelFactory
import com.example.papalote.viewModelFactory.UserViewModelFactory
import com.example.papalote.viewModelFactory.TriviaViewModelFactory
import com.example.papalote.api.Repository
import androidx.lifecycle.viewmodel.compose.viewModel



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tokenManager = TokenManager(applicationContext) // Inicializamos el TokenManager

        setContent {
            PapaloteTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(navController = navController, tokenManager = tokenManager)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController, tokenManager: TokenManager) {
    // Cambiamos la pantalla de inicio a "splash"
    NavHost(navController = navController, startDestination = "splash") {

        // Pantalla de Splash
        composable("splash") {
            SplashScreen(navController = navController, tokenManager = tokenManager)
        }

        // Pantalla de bienvenida
        composable("welcome") {
            WelcomeScreen(
                onLoginClicked = { navController.navigate("login") },
                onRegisterClicked = { navController.navigate("register") }
            )
        }
        val repository = Repository(RetrofitClient.apiService, tokenManager)
        composable("login") {
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(repository)
            )
            LoginScreen(
                tokenManager = tokenManager,
                viewModel = loginViewModel,
                onLoginSuccess = { navController.navigate("zones") },
                onBack = { navController.navigateUp() }
            )
        }

        // Pantalla de registro
        composable("register") {
            val registrationViewModel: RegistrationViewModel = viewModel(
                factory = RegistrationViewModelFactory(repository)
            )
            RegisterScreen(
                viewModel = registrationViewModel,
                onBack = { navController.navigateUp() },
                onRegistrationSuccess = { navController.navigate("welcome") }
            )
        }



        // Pantalla de Zonas
        composable("zones") {
            ZonasScreen(
                navController = navController,
                tokenManager = tokenManager,
                onBack = { navController.navigateUp() }
            )
        }

        //Pantalla de navegacion especifica para cada zona

        // Pantalla específica para Comprendo
        composable("comprendo") {
            ComprendoScreen(zoneName = "Comprendo" ,navController = navController, tokenManager = tokenManager)
        }

        composable("comunico") {
            ComunicoScreen(zoneName = "Comunico", navController = navController, tokenManager = tokenManager)
        }

        composable("expreso") {
            ExpresoScreen(zoneName = "Expreso",navController = navController, tokenManager = tokenManager)
        }

        composable("pequenos") {
            PequeñosScreen(zoneName = "Pequenos",navController = navController, tokenManager = tokenManager)
        }

        composable("pertenezco") {
            PertenezcoScreen(zoneName = "Pertenezco",navController = navController, tokenManager = tokenManager)
        }

        composable("soy") {
            SoyScreen(zoneName = "Soy",navController = navController, tokenManager = tokenManager)
        }

        // Pantalla de Detalle de Zona
        //composable("zoneDetail/{zoneName}") { backStackEntry ->
       //     val zoneName = backStackEntry.arguments?.getString("zoneName") ?: "Zona Desconocida"
     //       ZoneDetailScreen(
   //             zoneName = zoneName,
 //               onBack = { navController.navigateUp() },
//                navController = navController,
//                tokenManager = tokenManager,
//                onMedalClick = { navController.navigate("insignias") }
//            )
//        }

        // Pantalla de Insignias
        composable("insignias") {
            InsigniasScreen(
                navController = navController,
                tokenManager = tokenManager // Pasa el tokenManager aquí
            )
        }

        // Pantalla de Escaneo de QR
        composable("scanQR") {
            EscaneoQRScreen(
                navController = navController,
            )
        }

        // Pantalla de Dinosaurio (ComunicoScreen)
        //composable("dinosaur/{zoneName}") { backStackEntry ->
            //val zoneName = backStackEntry.arguments?.getString("zoneName") ?: "Zona Desconocida"
            //ComunicoScreen(
           //     zoneName = zoneName,
         //       onBack = { navController.navigateUp() },
       //         navController = navController,
     //           tokenManager = tokenManager
   //         )
 //       }
        // Pantalla de Mapa
        composable("mapa") {
            MapaScreen(
                navController = navController,
                onBack = { navController.navigateUp() } // Solución: Parámetros añadidos
            )
        }
        composable("profile") {
            val userViewModel = UserViewModelFactory(tokenManager).create(UserViewModel::class.java)
            UserProfileScreen(viewModel = userViewModel)
        }

        //Para la navegacion del comprendoscreen
        val triviaViewModelFactory = TriviaViewModelFactory(repository)
        composable("comprendo_questions/{activityName}") { backStackEntry ->
            val activityName = backStackEntry.arguments?.getString("activityName") ?: "Comprendo 1"
            ComprendoQuestionsScreen(
                navController = navController,
                tokenManager = tokenManager,
                activityName = activityName,
                triviaViewModelFactory = triviaViewModelFactory
            )
        }

        //Para la navegacion del comunicoscreen
        //val triviaViewModelFactory = TriviaViewModelFactory(repository)
        composable("comunico_questions/{activityName}") { backStackEntry ->
            val activityName = backStackEntry.arguments?.getString("activityName") ?: "Comunico 1"
            ComunicoQuestionsScreen(
                navController = navController,
                tokenManager = tokenManager,
                activityName = activityName,
                triviaViewModelFactory = triviaViewModelFactory
            )
        }

        //Para la navegacion del expresoscreen
        //val triviaViewModelFactory = TriviaViewModelFactory(repository)
        composable("expreso_questions/{activityName}") { backStackEntry ->
            val activityName = backStackEntry.arguments?.getString("activityName") ?: "Expreso 1"
            ExpresoQuestionsScreen(
                navController = navController,
                tokenManager = tokenManager,
                activityName = activityName,
                triviaViewModelFactory = triviaViewModelFactory
            )
        }

        //Para la navegacion del pequeñoscreen
        //val triviaViewModelFactory = TriviaViewModelFactory(repository)
        composable("pequeños_questions/{activityName}") { backStackEntry ->
            val activityName = backStackEntry.arguments?.getString("activityName") ?: "Pequeños 1"
            PequeñosQuestionsScreen(
                navController = navController,
                tokenManager = tokenManager,
                activityName = activityName,
                triviaViewModelFactory = triviaViewModelFactory
            )
        }

        //Para la navegacion del pertenezcoscreen
        //val triviaViewModelFactory = TriviaViewModelFactory(repository)
        composable("pertenezco_questions/{activityName}") { backStackEntry ->
            val activityName = backStackEntry.arguments?.getString("activityName") ?: "Pertenezco 1"
            PertenezcoQuestionsScreen(
                navController = navController,
                tokenManager = tokenManager,
                activityName = activityName,
                triviaViewModelFactory = triviaViewModelFactory
            )
        }

        //Para la navegacion del soyscreen
        //val triviaViewModelFactory = TriviaViewModelFactory(repository)
        composable("soy_questions/{activityName}") { backStackEntry ->
            val activityName = backStackEntry.arguments?.getString("activityName") ?: "Soy 1"
            SoyQuestionsScreen(
                navController = navController,
                tokenManager = tokenManager,
                activityName = activityName,
                triviaViewModelFactory = triviaViewModelFactory
            )
        }

    }
}

