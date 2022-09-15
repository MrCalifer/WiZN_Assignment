package edu.califer.wiznassignment.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.califer.wiznassignment.persistance.DAO.MovieDao
import edu.califer.wiznassignment.persistance.Entities.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)

abstract class Database : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}