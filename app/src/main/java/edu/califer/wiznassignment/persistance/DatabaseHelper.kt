package edu.califer.wiznassignment.persistance

import edu.califer.wiznassignment.persistance.Entities.MovieEntity

interface DatabaseHelper {
    suspend fun insertMovie(movieEntity: MovieEntity)
    suspend fun deleteMovie(movieEntity: MovieEntity)
    suspend fun updateFavourite(movieEntity: MovieEntity)
    suspend fun getAllMovies():List<MovieEntity>?
    suspend fun deleteMovieTable()
}