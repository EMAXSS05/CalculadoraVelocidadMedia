package com.example.calculadora_de_velocidademedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculoViewModel : ViewModel() {


    private val _velKmH = MutableLiveData<String>("0 km/h")
    val velKmH: LiveData<String> get() = _velKmH


    private val _velMs = MutableLiveData<String>("0 m/s")
    val velMs: LiveData<String> get() = _velMs

    // LiveData para errores
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error


    fun actualizarDatos(distanciaStr: String, tiempoStr: String) {

        val distancia = distanciaStr.toFloatOrNull()
        val tiempo = tiempoStr.toFloatOrNull()

        // Validación
        if (distancia == null || tiempo == null) {
            _error.value = "Os valores deben ser numéricos."
            return
        }

        if (distancia < 0 || tiempo <= 0) {
            _error.value = "A distancia non pode ser negativa e o tempo debe ser maior de 0."
            return
        }

        _error.value = null

        // Cálculo km/h
        val velocidadKmH = distancia / tiempo

        // Cálculo m/s
        val distancia_metros = distancia * 1000
        val tiempo_segundos = tiempo * 3600
        val velocidadMs = distancia_metros / tiempo_segundos

        // Actualizar LiveData
        _velKmH.value = String.format("%.2f km/h", velocidadKmH)
        _velMs.value = String.format("%.2f m/s", velocidadMs)
    }
}
