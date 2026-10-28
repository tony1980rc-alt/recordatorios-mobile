package com.example.recordatorios

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.CancellationException

class CrearRecordatorioViewModel(
    private val repositorio: RecordatorioRepository =
        ContenedorApp.repositorio
) : ViewModel() {

    private val _uiState = MutableStateFlow(CrearRecordatorioUiState())

    val uiState = _uiState.asStateFlow()

    fun cambiarTitulo(nuevoTitulo: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(titulo = nuevoTitulo)
        }
    }

    fun cambiarDescripcion(nuevaDescripcion: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(descripcion = nuevaDescripcion)
        }
    }

    fun cambiarCategoria(nuevaCategoria: Categoria) {
        _uiState.update { estadoActual ->
            estadoActual.copy(categoria = nuevaCategoria)
        }
    }

    fun cambiarPrioridad(nuevaPrioridad: Prioridad) {
        _uiState.update { estadoActual ->
            estadoActual.copy(prioridad = nuevaPrioridad)
        }
    }
    fun guardar() {
        val datos = _uiState.value

        if (datos.guardando || datos.guardado) return

        val categoria = datos.categoria
        val prioridad = datos.prioridad

        if (
            datos.titulo.trim().length < 4 ||
            datos.descripcion.length > 200 ||
            categoria !in listOf(categoriaEstudio, categoriaTrabajo) ||
            categoria == null ||
            prioridad == null
        ) {
            _uiState.update {
                it.copy(error = "Revisá los datos del formulario")
            }
            return
        }

        _uiState.update {
            it.copy(guardando = true, error = null)
        }

        viewModelScope.launch {
            try {
                val nuevoRecordatorio = Recordatorio(
                    titulo = datos.titulo.trim(),
                    descripcion = datos.descripcion.ifBlank { null },
                    categoria = categoria,
                    prioridad = prioridad,
                    estado = EstadoRecordatorio.Pendiente
                )

                repositorio.guardarRecordatorio(nuevoRecordatorio)

                _uiState.update {
                    it.copy(guardando = false, guardado = true)
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        guardando = false,
                        error = "No se pudo guardar. Intentá nuevamente."
                    )
                }
            }
        }
    }
    fun reiniciarFormulario() {
        _uiState.value = CrearRecordatorioUiState()
    }
}