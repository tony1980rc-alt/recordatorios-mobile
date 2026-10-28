package com.example.recordatorios



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaDetalleRecordatorio(
    recordatorio: Recordatorio,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(text = "Detalle del recordatorio")

        TarjetaRecordatorio(recordatorio = recordatorio)

        Text(
            text = "Descripción: ${
                recordatorio.descripcion ?: "Sin descripción"
            }"
        )

        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}