package com.example.recordatorios

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MensajeEstado(
    mensaje: String,
    modifier: Modifier = Modifier,
    cargando: Boolean = false,
    textoAccion: String? = null,
    onAccion: (() -> Unit)? = null
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (cargando) {
            CircularProgressIndicator()
        }

        Text(text = mensaje)

        if (textoAccion != null && onAccion != null) {
            Button(onClick = onAccion) {
                Text(text = textoAccion)
            }
        }
    }
}