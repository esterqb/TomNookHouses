package com.example.tomnookhouses.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CasaDao {
    @Query("SELECT * FROM tabla_casas")
    fun obtenerTodas(): Flow<List<Casa>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(casa: Casa)

    @Delete
    suspend fun borrar(casa: Casa)
}