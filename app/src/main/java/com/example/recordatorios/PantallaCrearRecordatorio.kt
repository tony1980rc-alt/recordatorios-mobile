package com.example.recordatorios

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.imePadding

@Composable
fun PantallaCrearRecordatorio(
    titulo: String,
    onTituloChange: (String) -> Unit,
    descripcion: String,
    onDescripcionChange: (String) -> Unit,
    categoria: Categoria?,
    onCategoriaChange: (Categoria) -> Unit,
    prioridad: Prioridad?,
    onPrioridadChange: (Prioridad) -> Unit,
    guardando: Boolean,
    onGuardar: () -> Unit,
    onCancelar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        val tituloValido = titulo.trim().length >= 4
        val descripcionValida = descripcion.length <= 200
        Text(text = "Nuevo recordatorio")

        OutlinedTextField(
            value = titulo,
            onValueChange = onTituloChange,
            label = { Text("Título") },
            singleLine = true,
            isError = titulo.isNotEmpty() && !tituloValido,
            supportingText = {
                Text("Mínimo 4 caracteres")
            }
        )
        OutlinedTextField(
            value = descripcion,
            onValueChange = onDescripcionChange,
            label = { Text("Descripción") },
            isError = !descripcionValida,
            supportingText = {
                Text("${descripcion.length}/200 caracteres")
            }
        )
        val formularioValido =
            tituloValido &&
                    descripcionValida &&
                    categoria in listOf(categoriaEstudio, categoriaTrabajo) &&
                    prioridad != null

        Text(text = "Categoría: ${categoria?.nombre ?: "Sin seleccionar"}")

        Button(onClick = { onCategoriaChange(categoriaEstudio) }) {
            Text("Estudio")
        }

        Button(onClick = { onCategoriaChange(categoriaTrabajo) }) {
            Text("Trabajo")
        }
        val textoPrioridad = when (prioridad) {
            Prioridad.Alta -> "Alta"
            Prioridad.Media -> "Media"
            Prioridad.Baja -> "Baja"
            null -> "Sin seleccionar"
        }

        Text(text = "Prioridad: $textoPrioridad")

        Button(onClick = { onPrioridadChange(Prioridad.Alta) }) {
            Text("Alta")
        }

        Button(onClick = { onPrioridadChange(Prioridad.Media) }) {
            Text("Media")
        }

        Button(onClick = { onPrioridadChange(Prioridad.Baja) }) {
            Text("Baja")
        }
        Button(
            onClick = onGuardar,
            enabled = formularioValido && !guardando
        ) {
            Text(text = if (guardando) "Guardando..." else "Guardar")
        }

        Button(onClick = onCancelar) {
            Text(text = "Cancelar")
        }
    }
}