package com.example.recordatorios

val categoriaEstudio = Categoria(nombre = "Estudio")
val categoriaTrabajo = Categoria(nombre = "Trabajo")

val recordatorio1 = Recordatorio(
    titulo = "Entregar actividad de Mobile",
    descripcion = null,
    categoria = categoriaEstudio,
    prioridad = Prioridad.Alta,
    estado = EstadoRecordatorio.Pendiente
)
val recordatorio2 = Recordatorio(
    titulo = "Actualizar manual",
    descripcion = "Revisar cambios",
    categoria = categoriaTrabajo,
    prioridad = Prioridad.Media,
    estado = EstadoRecordatorio.Completado
)

val recordatorios = listOf(recordatorio1, recordatorio2)

val pendientes = recordatorios.filter {
    it.estado == EstadoRecordatorio.Pendiente
}

val prioridadAlta = recordatorios.filter {
    it.prioridad == Prioridad.Alta
}

val categorias = setOf(
    categoriaEstudio,
    categoriaTrabajo,
    categoriaEstudio
)

