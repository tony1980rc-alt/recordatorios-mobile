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
    val estadoPantalla: EstadoPantalla =
        EstadoPantalla.ConDatos(recordatorios)
    val coloresCategorias = mapOf(
        categoriaEstudio to Color(0xFF1565C0),
        categoriaTrabajo to Color(0xFF2E7D32)
    )
    val contexto = LocalContext.current
    var mensajeSeleccion by remember { mutableStateOf("") }
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = mensajeSeleccion)
        when (estadoPantalla) {
            EstadoPantalla.Cargando -> {
                Text(text = "Cargando…")
            }

            is EstadoPantalla.Error -> {
                Text(text = estadoPantalla.mensaje)
            }

            is EstadoPantalla.ConDatos -> {
                estadoPantalla.recordatorios.forEach { recordatorio ->
                    TarjetaRecordatorio(
                        recordatorio = recordatorio,
                        onClick = {
                            mensajeSeleccion = "Seleccionaste: ${recordatorio.titulo}"
                        }
                    )









                    Spacer(modifier = Modifier.height(16.dp))
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