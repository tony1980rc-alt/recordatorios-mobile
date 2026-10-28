package com.example.recordatorios

interface RecordatorioRepository {
    suspend fun obtenerRecordatorios(): List<Recordatorio>

    suspend fun guardarRecordatorio(recordatorio: Recordatorio)

    suspend fun obtenerRecordatorioPorId(id: String): Recordatorio?
}