package com.example.recordatorios

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

@Composable
fun PantallaListado(
    recordatorios: List<Recordatorio>,
    onRecordatorioClick: (Recordatorio) -> Unit,
    onCrearRecordatorio: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 96.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recordatorios) { recordatorio ->
                TarjetaRecordatorio(
                    recordatorio = recordatorio,
                    onClick = {
                        onRecordatorioClick(recordatorio)
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = onCrearRecordatorio,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = "+")
        }
    }
}