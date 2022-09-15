package edu.califer.wiznassignment.persistance

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    private var instance: Database? = null

    fun getInstance(context: Context): Database {
        if (instance == null) {
            synchronized(Database::class) {
                instance = buildDB(context)
            }
        }
        return instance!!
    }

    private fun buildDB(context: Context): Database {
        return Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "database-db"
        ).build()
    }
}