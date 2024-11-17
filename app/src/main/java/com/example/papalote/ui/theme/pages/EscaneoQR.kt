package com.example.papalote.ui.theme.pages

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavHostController
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.example.papalote.utils.LanguageManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EscaneoQRScreen(navController: NavHostController) {
    val context = LocalContext.current
    var scanResult by remember { mutableStateOf<String?>(null) }
    var showError by remember { mutableStateOf(false) }

    // Obtener el idioma actual
    val currentLanguage = LanguageManager.language

    // Textos dinámicos basados en el idioma
    val screenTitle = if (currentLanguage == "es") "Escaneo de QR" else "QR Scan"
    val scanButtonText = if (currentLanguage == "es") "Escanear Código QR" else "Scan QR Code"
    val qrContentText = if (currentLanguage == "es") "Contenido del QR:" else "QR Content:"
    val openBrowserText = if (currentLanguage == "es") "Abrir en Navegador" else "Open in Browser"
    val errorText = if (currentLanguage == "es") "Error al escanear el código QR." else "Error scanning the QR code."
    val backButtonText = if (currentLanguage == "es") "Volver al Menú" else "Back to Menu"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(screenTitle) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = if (currentLanguage == "es") "Volver" else "Back")
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón para iniciar el escaneo
            Button(onClick = { scanQR(context) { result, error ->
                scanResult = result
                showError = error
            } }) {
                Text(scanButtonText)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar el resultado del QR
            if (scanResult != null) {
                Text("$qrContentText $scanResult", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(scanResult))
                    context.startActivity(browserIntent)
                }) {
                    Text(openBrowserText)
                }
            }

            // Mostrar error si ocurre
            if (showError) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = errorText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para regresar al menú principal
            Button(onClick = { navController.navigate("welcome") }) {
                Text(backButtonText)
            }
        }
    }
}

// Función para escanear el código QR
fun scanQR(context: Context, onResult: (String?, Boolean) -> Unit) {
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_AZTEC
        )
        .build()

    val scanner = GmsBarcodeScanning.getClient(context)

    scanner.startScan()
        .addOnSuccessListener { barcode ->
            val url = barcode.rawValue
            if (!url.isNullOrEmpty()) {
                Log.d("TAG", "Escaneo exitoso: $url")
                onResult(url, false)
            } else {
                Log.d("TAG", "El código QR no contiene datos válidos.")
                onResult(null, true)
            }
        }
        .addOnCanceledListener {
            Log.d("TAG", "Escaneo cancelado.")
            onResult(null, true)
        }
        .addOnFailureListener { e ->
            Log.d("TAG", "Error al escanear: ${e.message}")
            onResult(null, true)
        }
}