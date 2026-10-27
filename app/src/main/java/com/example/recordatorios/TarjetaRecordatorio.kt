package com.example.recordatorios

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TarjetaRecordatorio(
    recordatorio: Recordatorio,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = recordatorio.titulo,
                style = MaterialTheme.typography.titleLarge
            )

            Text(text = recordatorio.categoria.nombre)

            val textoPrioridad = when (recordatorio.prioridad) {
                Prioridad.Alta -> "Alta"
                Prioridad.Media -> "Media"
                Prioridad.Baja -> "Baja"
            }

            Text(text = "Prioridad: $textoPrioridad")

            val textoEstado = when (recordatorio.estado) {
                EstadoRecordatorio.Pendiente -> "Pendiente"
                EstadoRecordatorio.Completado -> "Completado"
            }

            Text(text = "Estado: $textoEstado")
        }
    }
}
