package com.example.tomnookhouses.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Casa::class], version = 1, exportSchema = false)
abstract class CasaDatabase : RoomDatabase() {
    abstract fun casaDao(): CasaDao

    companion object {
        @Volatile
        private var INSTANCE: CasaDatabase? = null

        fun getDatabase(context: Context): CasaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CasaDatabase::class.java,
                    "tom_nook_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}