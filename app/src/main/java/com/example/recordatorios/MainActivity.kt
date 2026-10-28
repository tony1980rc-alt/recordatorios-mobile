package com.example.recordatorios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recordatorios.ui.theme.RecordatoriosTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecordatoriosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val listadoViewModel: ListadoViewModel = viewModel()
    val estadoListado by
    listadoViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = "listado",
        modifier = modifier.fillMaxSize()
    ) {
        composable("listado") {
            LaunchedEffect(Unit) {
                listadoViewModel.cargar()
            }

            when (val estado = estadoListado) {
                EstadoPantalla.Vacio -> {
                    Column {
                        Text(text = "No tenés recordatorios")

                        androidx.compose.material3.Button(
                            onClick = {
                                navController.navigate("crear")
                            }
                        ) {
                            Text("Crear recordatorio")
                        }
                    }
                }
                EstadoPantalla.Cargando -> {
                    Text(text = "Cargando...")
                }

                is EstadoPantalla.Error -> {
                    Column {
                        Text(text = estado.mensaje)

                        androidx.compose.material3.Button(
                            onClick = {
                                listadoViewModel.cargar()
                            }
                        ) {
                            Text("Reintentar")
                        }
                    }
                }

                is EstadoPantalla.ConDatos -> {
                    PantallaListado(
                        recordatorios = estado.recordatorios,
                        onRecordatorioClick = { recordatorio ->
                            navController.navigate(
                                "detalle/${recordatorio.id}"
                            )
                        },
                        onCrearRecordatorio = {
                            navController.navigate("crear")
                        }
                    )
                }
            }
        }

        composable("crear") {
            val crearViewModel: CrearRecordatorioViewModel = viewModel()
            val estadoFormulario by
            crearViewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(estadoFormulario.guardado) {
                if (estadoFormulario.guardado) {
                    navController.popBackStack()
                }
            }

            Column {
                estadoFormulario.error?.let { mensaje ->
                    Text(text = mensaje)
                }

                PantallaCrearRecordatorio(
                    titulo = estadoFormulario.titulo,
                    onTituloChange = { nuevoTexto ->
                        crearViewModel.cambiarTitulo(nuevoTexto)
                    },
                    descripcion = estadoFormulario.descripcion,
                    onDescripcionChange = { nuevoTexto ->
                        crearViewModel.cambiarDescripcion(nuevoTexto)
                    },
                    categoria = estadoFormulario.categoria,
                    onCategoriaChange = { categoriaElegida ->
                        crearViewModel.cambiarCategoria(categoriaElegida)
                    },
                    prioridad = estadoFormulario.prioridad,
                    onPrioridadChange = { prioridadElegida ->
                        crearViewModel.cambiarPrioridad(prioridadElegida)
                    },
                    guardando = estadoFormulario.guardando,
                    onGuardar = {
                        crearViewModel.guardar()
                    },
                    onCancelar = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        composable(
            route = "detalle/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { entrada ->
            val id = entrada.arguments?.getString("id").orEmpty()

            val detalleViewModel: DetalleRecordatorioViewModel = viewModel()
            val estadoDetalle by
            detalleViewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(id) {
                detalleViewModel.cargar(id)
            }

            when (val estado = estadoDetalle) {
                DetalleUiState.Cargando -> {
                    Text(text = "Cargando detalle...")
                }

                is DetalleUiState.Error -> {
                    Column {
                        Text(text = estado.mensaje)

                        androidx.compose.material3.Button(
                            onClick = { detalleViewModel.cargar(id) }
                        ) {
                            Text("Reintentar")
                        }

                        androidx.compose.material3.Button(
                            onClick = { navController.popBackStack() }
                        ) {
                            Text("Volver")
                        }
                    }
                }

                is DetalleUiState.ConDatos -> {
                    PantallaDetalleRecordatorio(
                        recordatorio = estado.recordatorio,
                        onVolver = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecordatoriosTheme {
        Greeting("Android")
    }
}