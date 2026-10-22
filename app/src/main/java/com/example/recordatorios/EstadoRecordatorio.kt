package com.example.recordatorios

sealed class EstadoRecordatorio {
    object Pendiente : EstadoRecordatorio()
    object Completado : EstadoRecordatorio()
}