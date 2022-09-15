package edu.califer.wiznassignment.persistance.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.califer.wiznassignment.api.Models.MovieModel
import edu.califer.wiznassignment.persistance.Entities.MovieEntity

@Dao
interface MovieDao{

    @Insert
    suspend fun insertDao(movieEntity: MovieEntity)

    @Delete
    suspend fun deleteDao(movieEntity: MovieEntity)

    @Query("UPDATE movie_table SET isFavourite = :isFavourite WHERE id = :id")
    suspend fun updateFavourite(isFavourite: Boolean, id :String)

    @Query("SELECT * FROM movie_table")
    suspend fun getAllMovies() : List<MovieEntity>

    @Query("DELETE FROM movie_table")
    suspend fun nukeMovieTable()
}