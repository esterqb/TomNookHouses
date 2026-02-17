package com.example.tomnookhouses.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_casas")
data class Casa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val imagenID: Int? = 0,
    val descripcion: String,
    val imagenUri: String?,

    val decor1: Int? = null,
    val decor2: Int? = null,
    val decor3: Int? = null,
    val decor4: Int? = null
)