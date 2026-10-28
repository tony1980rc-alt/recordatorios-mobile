package com.example.recordatorios



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetalleUiState {
    object Cargando : DetalleUiState()

    data class ConDatos(
        val recordatorio: Recordatorio
    ) : DetalleUiState()

    data class Error(
        val mensaje: String
    ) : DetalleUiState()
}

class DetalleRecordatorioViewModel(
    private val repositorio: RecordatorioRepository =
        ContenedorApp.repositorio
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<DetalleUiState>(DetalleUiState.Cargando)

    val uiState = _uiState.asStateFlow()

    fun cargar(id: String) {
        _uiState.value = DetalleUiState.Cargando

        viewModelScope.launch {
            try {
                val recordatorio =
                    repositorio.obtenerRecordatorioPorId(id)

                _uiState.value = if (recordatorio != null) {
                    DetalleUiState.ConDatos(recordatorio)
                } else {
                    DetalleUiState.Error("No se encontró el recordatorio")
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value =
                    DetalleUiState.Error("No se pudo cargar el detalle")
            }
        }
    }
}