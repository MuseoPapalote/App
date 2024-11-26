package com.example.papalote.ui.theme.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.papalote.ui.theme.components.CustomBottomBar
import com.example.papalote.ui.theme.components.ZoneHeader
import com.example.papalote.utils.TokenManager
import com.example.papalote.ui.theme.components.ZoneDetailScreen
import com.example.papalote.R
import com.example.papalote.ui.theme.components.QuestionCard
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx. lifecycle. viewmodel. compose. viewModel
import com.example.papalote.viewModelFactory.TriviaViewModelFactory
import com.example.papalote.viewModel.TriviaViewModel
import com.example.papalote.states.TriviaAnswerState
import com.example.papalote.states.TriviaQuestionState


@Composable
fun ExpresoScreen(
    navController: NavHostController,
    tokenManager: TokenManager,
    zoneName: String, // Agregar este parámetro
) {
    ZoneDetailScreen(
        zoneName = "Expreso",
        backgroundColor = Color(0xFF7C55C7),
        headerImage = R.drawable.comprendologo,
        activities = listOf("Expreso 1", "Expreso 2", "Expreso 3"),
        onMedalClick = { /* Acción específica para medallas */ },
        navController = navController,
        tokenManager = tokenManager,
        onBack = { navController.navigateUp() },
        onActivityClick = { activityName ->
            navController.navigate("expreso_questions/$activityName")
        }
    )
}

@Composable
fun ExpresoQuestionsScreen(
    navController: NavHostController,
    tokenManager: TokenManager,
    activityName: String, // Activity name to differentiate sets of questions
    triviaViewModelFactory: TriviaViewModelFactory
) {
    val triviaViewModel: TriviaViewModel = viewModel(factory = triviaViewModelFactory)

    // Observa el estado de las preguntas y respuestas desde el ViewModel
    val triviaQuestionState by triviaViewModel.triviaQuestionState.collectAsState()
    val triviaAnswerState by triviaViewModel.triviaAnswerState.collectAsState()

    val currentQuestionIndex = remember { mutableStateOf(0) } // Tracks current question
    val isTransitioning = remember { mutableStateOf(false) } // Controlar la transición

    val zoneName = "Expreso"

    // Inicia la carga de preguntas
    LaunchedEffect(Unit) {
        println("Enviando zona: $zoneName")
        triviaViewModel.obtenerPreguntasPorZona(zoneName)
    }

    Scaffold(
        bottomBar = {
            CustomBottomBar(navController = navController, tokenManager = tokenManager)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (triviaQuestionState) {
                is TriviaQuestionState.Loading -> {
                    println("Cargando Preguntas...")
                    CircularProgressIndicator(color = Color(0xFF7C55C7))
                }
                is TriviaQuestionState.Success -> {
                    val preguntas = (triviaQuestionState as TriviaQuestionState.Success).preguntas

                    if (currentQuestionIndex.value < preguntas.size) {
                        val pregunta = preguntas[currentQuestionIndex.value]

                        // Mostrar tarjeta de pregunta
                        QuestionCard(
                            questionNumber = currentQuestionIndex.value + 1,
                            questionText = pregunta.texto_pregunta,
                            options = listOf(pregunta.opcion_1, pregunta.opcion_2, pregunta.opcion_3),
                            correctOptionIndex = pregunta.respuesta_correcta,
                            imageResource = R.drawable.dinosaur_image,
                            onAnswer = { seleccionUsuario ->
                                println("onAnswer llamado con selección: $seleccionUsuario")
                                if (!isTransitioning.value) {
                                    isTransitioning.value = true

                                    // Calcular el índice seleccionado
                                    val opcionSeleccionada = seleccionUsuario + 1

                                    println("Pregunta actual: ${pregunta.texto_pregunta}, Respuesta correcta: ${pregunta.respuesta_correcta}")
                                    println("Opción seleccionada: $opcionSeleccionada")

                                    println("Preparando respuesta: id_pregunta = ${pregunta.id_pregunta}, opcion_seleccionada = $opcionSeleccionada")

                                    val esCorrecta = pregunta.respuesta_correcta == opcionSeleccionada
                                    println("Respuesta localmente correcta: $esCorrecta")

                                    // Enviar respuesta al ViewModel
                                    triviaViewModel.enviarRespuestaTrivia(
                                        idPregunta = pregunta.id_pregunta,
                                        opcionSeleccionada = opcionSeleccionada
                                    )
                                }
                            },
                            onNextQuestion = {
                                if (!isTransitioning.value) {
                                    currentQuestionIndex.value++
                                    isTransitioning.value = false
                                }
                            }
                        )

                        // Manejo de estados de respuesta
                        when (triviaAnswerState) {
                            is TriviaAnswerState.Loading -> {
                                println("Enviando respuesta...")
                            }
                            is TriviaAnswerState.Success -> {
                                val esCorrecta = (triviaAnswerState as TriviaAnswerState.Success).esCorrecta

                                println("Respuesta enviada correctamente. ¿Correcta? $esCorrecta")
                                if (esCorrecta) {
                                    println("¡Respuesta correcta para la pregunta '${pregunta.texto_pregunta}'!")
                                } else {
                                    println("Respuesta incorrecta para la pregunta '${pregunta.texto_pregunta}'.")
                                }

                                // Retraso para mostrar el estado antes de pasar
                                LaunchedEffect(esCorrecta) {
                                    kotlinx.coroutines.delay(2000)
                                    currentQuestionIndex.value++
                                    isTransitioning.value = false
                                }
                            }
                            is TriviaAnswerState.Error -> {
                                val errorMessage = (triviaAnswerState as TriviaAnswerState.Error).message
                                println("Error al enviar respuesta: $errorMessage")
                                isTransitioning.value = false
                            }
                            else -> {}
                        }
                    } else {
                        // Pantalla de finalización
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "¡Has completado todas las preguntas!",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { navController.navigateUp() },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7C55C7))
                            ) {
                                Text(
                                    text = "Volver",
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
                is TriviaQuestionState.Error -> {
                    val errorMessage = (triviaQuestionState as TriviaQuestionState.Error).message
                    Text(
                        text = "Error: $errorMessage",
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                else -> {
                    Text(text = "Cargando...", color = Color.Gray)
                }
            }
        }
    }
}




