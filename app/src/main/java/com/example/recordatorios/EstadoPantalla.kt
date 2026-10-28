package com.example.recordatorios

sealed class EstadoPantalla {
    object Cargando : EstadoPantalla()

    object Vacio : EstadoPantalla()

    data class ConDatos(
        val recordatorios: List<Recordatorio>
    ) : EstadoPantalla()

    data class Error(
        val mensaje: String
    ) : EstadoPantalla()
}