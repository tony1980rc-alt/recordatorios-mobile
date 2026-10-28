package com.example.recordatorios

import java.util.UUID

data class Recordatorio(
    val titulo: String,
    val descripcion: String?,
    val categoria: Categoria,
    val prioridad: Prioridad,
    val estado: EstadoRecordatorio,
    val id: String = UUID.randomUUID().toString()
) {
    init {
        require(titulo.isNotBlank()) {
            "El título no puede estar vacío"
        }
    }
}