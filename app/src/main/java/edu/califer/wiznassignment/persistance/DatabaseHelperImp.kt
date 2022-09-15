package edu.califer.wiznassignment.persistance

import edu.califer.wiznassignment.persistance.Entities.MovieEntity

class DatabaseHelperImp(private val database : Database) : DatabaseHelper {
    override suspend fun insertMovie(movieEntity: MovieEntity) {
        database.movieDao().insertDao(movieEntity)
    }

    override suspend fun deleteMovie(movieEntity: MovieEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateFavMovie(movieEntity: MovieEntity) {
        TODO("Not yet implemented")
    }
}