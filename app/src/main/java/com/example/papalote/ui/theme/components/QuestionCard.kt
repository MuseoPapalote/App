package com.example.papalote.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material.*
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch





@Composable
fun QuestionCard(
    questionNumber: Int,
    questionText: String,
    options: List<String>,
    correctOptionIndex: Int,
    imageResource: Int,
    onAnswer: (seleccionUsuario: Int) -> Unit,
    onNextQuestion: () -> Unit // Callback para avanzar a la siguiente pregunta
) {
    val selectedOption = remember { mutableStateOf<Int?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val isProcessingAnswer = remember { mutableStateOf(false) }
    val isAnswerProcessed = remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen asociada a la pregunta
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Question Image",
            modifier = Modifier
                .size(250.dp)
                .padding(16.dp)
                .background(Color(0xFF4CA6A8), shape = RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

        // Texto de la pregunta
        Text(
            text = questionText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(vertical = 16.dp)
        )

        // Opciones de respuesta
        options.forEachIndexed { index, option ->
            // Convertir el índice actual (base 0) a base 1
            val indexBase1 = index + 1

            // val isSelected = selectedOption.value == index
            // Determinar si la opción es correcta
            val isCorrect = indexBase1 == correctOptionIndex

            val backgroundColor = when {
                selectedOption.value == indexBase1 && isCorrect -> Color.Green
                selectedOption.value == indexBase1 && !isCorrect -> Color.Red
                else -> Color(0xFFD0D0D0)
            }

            Button(
                onClick = {
                    if (!isProcessingAnswer.value && !isAnswerProcessed.value) {
                        isProcessingAnswer.value = true // Bloquear nuevas interacciones
                        selectedOption.value = indexBase1

                        println("Opción seleccionada: $indexBase1, Respuesta correcta: $correctOptionIndex")

                        // Ajustar el índice seleccionado a la base de 1 para el backend
                        //val opcionSeleccionada = index + 1
                        //println("Seleccionada: $opcionSeleccionada, Correcta: $correctOptionIndex (Ajustada: $adjustedCorrectOptionIndex)")

                        onAnswer(indexBase1) // Notificar la respuesta

                        coroutineScope.launch {
                            kotlinx.coroutines.delay(2000) // Mostrar el coloreo por 2 segundos
                            selectedOption.value = null // Restablecer la selección
                            isAnswerProcessed.value = true // Permitir avanzar a la siguiente pregunta
                            isProcessingAnswer.value = false // Permitir nuevas interacciones
                            onNextQuestion() // Pasar a la siguiente pregunta
                            isAnswerProcessed.value = false // Resetear el flag después del cambio
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = option,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}
