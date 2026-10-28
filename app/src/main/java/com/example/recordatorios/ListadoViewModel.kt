package com.example.recordatorios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListadoViewModel(
    private val repositorio: RecordatorioRepository =
        ContenedorApp.repositorio
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<EstadoPantalla>(EstadoPantalla.Cargando)

    val uiState = _uiState.asStateFlow()



    fun cargar() {
        _uiState.value = EstadoPantalla.Cargando

        viewModelScope.launch {
            try {
                val lista = repositorio.obtenerRecordatorios()

                _uiState.value = if (lista.isEmpty()) {
                    EstadoPantalla.Vacio
                } else {
                    EstadoPantalla.ConDatos(lista)
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value =
                    EstadoPantalla.Error(
                        "No se pudieron cargar los recordatorios"
                    )
            }
        }
    }
}