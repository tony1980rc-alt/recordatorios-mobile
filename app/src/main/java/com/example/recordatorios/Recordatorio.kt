package com.example.recordatorios

data class Recordatorio(
    val titulo: String,
    val descripcion: String?,
    val categoria: Categoria,
    val prioridad: Prioridad,
    val estado: EstadoRecordatorio
)
{
    init {
        require(titulo.isNotBlank()) {
            "El título no puede estar vacío"
        }
    }
}