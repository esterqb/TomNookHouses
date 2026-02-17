package com.example.tomnookhouses.ui

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tomnookhouses.data.Casa
import com.example.tomnookhouses.data.CasaDatabase
import com.example.tomnookhouses.data.RepositorioCasas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CasaViewModel(application: Application) : AndroidViewModel(application) {
    val listaCasas = mutableStateListOf<Casa>()
    init {
        actualizarLista()
    }

    fun actualizarLista() {
        viewModelScope.launch(Dispatchers.IO) {
            RepositorioCasas.cargarDesdeDB(getApplication())

            withContext(Dispatchers.Main) {
                listaCasas.clear()
                listaCasas.addAll(RepositorioCasas.listaCasas)
            }
        }
    }

    fun insertarCasa(casa: Casa) {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = CasaDatabase.getDatabase(getApplication()).casaDao()
            dao.insertar(casa)
            actualizarLista()
        }
    }

    fun obtenerCasaPorId(id: Int): Casa? {
        return listaCasas.find { it.id == id }
    }
}