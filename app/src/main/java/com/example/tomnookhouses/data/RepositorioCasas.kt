package com.example.tomnookhouses.data

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.example.tomnookhouses.R
import kotlinx.coroutines.flow.first

object RepositorioCasas {
    val listaCasas = mutableStateListOf<Casa>()

    suspend fun cargarDesdeDB(context: Context) {
        val db = CasaDatabase.getDatabase(context)
        val dao = db.casaDao()

        val casasActuales = dao.obtenerTodas().first()

        if (casasActuales.isEmpty()) {
            val predeterminadas = listOf(
                Casa(
                    nombre = "Chalet Empedrado",
                    imagenID = R.drawable.house1,
                    descripcion = "Casa fachada con piedra, jardín y casita para perro.",
                    imagenUri = null,
                    decor1 = R.drawable.decor1,
                    decor2 = R.drawable.decor2,
                    decor3 = R.drawable.decor3,
                    decor4 = R.drawable.decor4
                ),
                Casa(
                    nombre = "Casa tejado marrón",
                    imagenID = R.drawable.house2,
                    descripcion = "Casa con porche delante del precipicio.",
                    imagenUri = null,
                    decor1 = R.drawable.decor1,
                    decor2 = R.drawable.decor2,
                    decor3 = R.drawable.decor3,
                    decor4 = R.drawable.decor4
                ),
                Casa(
                    nombre = "Casita con jardín",
                    imagenID = R.drawable.house3,
                    descripcion = "Casita con horno de piedra y mesa de jardín.",
                    imagenUri = null,
                    decor1 = R.drawable.decor1,
                    decor2 = R.drawable.decor2,
                    decor3 = R.drawable.decor3,
                    decor4 = R.drawable.decor4
                ),
                Casa(
                    nombre = "Casa azul",
                    imagenID = R.drawable.house4,
                    descripcion = "Perfecta para meditar y relajarse.",
                    imagenUri = null,
                    decor1 = R.drawable.decor1,
                    decor2 = R.drawable.decor2,
                    decor3 = R.drawable.decor3,
                    decor4 = R.drawable.decor4
                ),
                Casa(
                    nombre = "Casita ensueño",
                    imagenID = R.drawable.house5,
                    descripcion = "Casita creativa y cuca.",
                    imagenUri = null,
                    decor1 = R.drawable.decor1,
                    decor2 = R.drawable.decor2,
                    decor3 = R.drawable.decor3,
                    decor4 = R.drawable.decor4
                )
            )

            predeterminadas.forEach { dao.insertar(it) }
        }

        val casasDesdeDB = dao.obtenerTodas().first()
        listaCasas.clear()
        listaCasas.addAll(casasDesdeDB)
    }

    fun obtenerCasaPorId(id: Int): Casa? {
        return listaCasas.find { it.id == id }
    }
}