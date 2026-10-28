package com.example.recordatorios

class RecordatorioRepositoryEnMemoria : RecordatorioRepository {

    private val lista = recordatorios.toMutableList()
    private var simularError = false

    override suspend fun obtenerRecordatorios(): List<Recordatorio> {
        if (simularError) {
            simularError = false
            throw IllegalStateException("Error de prueba")
        }

        return lista.toList()
    }

    override suspend fun guardarRecordatorio(recordatorio: Recordatorio) {
        lista.add(recordatorio)
    }

    override suspend fun obtenerRecordatorioPorId(id: String): Recordatorio? {
        return lista.find { it.id == id }
    }
}