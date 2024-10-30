package com.example.papalote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.papalote.ui.theme.PapaloteTheme


import com.example.papalote.ui.theme.pages.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PapaloteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Llama a LoginScreen en lugar de Greeting
                    LoginScreen(
                        onLoginClick = {
                            // Acción al hacer clic en "Iniciar Sesión"
                            // Aquí podrías implementar la navegación o lógica adicional
                        }
                    )
                }
            }
        }
    }
}
