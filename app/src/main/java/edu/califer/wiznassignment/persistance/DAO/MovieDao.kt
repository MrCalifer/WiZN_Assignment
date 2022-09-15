package edu.califer.wiznassignment.persistance.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import edu.califer.wiznassignment.persistance.Entities.MovieEntity

@Dao
interface MovieDao{

    @Insert
    suspend fun insertDao(movieEntity: MovieEntity)

    @Delete
    suspend fun deleteDao(movieEntity: MovieEntity)


}