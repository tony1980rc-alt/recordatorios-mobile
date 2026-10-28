package com.example.recordatorios

data class CrearRecordatorioUiState(
    val titulo: String = "",
    val descripcion: String = "",
    val categoria: Categoria? = null,
    val prioridad: Prioridad? = null,
    val guardando: Boolean = false,
    val guardado: Boolean = false,
    val error: String? = null
)