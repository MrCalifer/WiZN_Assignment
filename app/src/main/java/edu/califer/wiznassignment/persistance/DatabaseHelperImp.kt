package edu.califer.wiznassignment.persistance

import edu.califer.wiznassignment.persistance.Entities.MovieEntity

class DatabaseHelperImp(private val database : Database) : DatabaseHelper {
    override suspend fun insertMovie(movieEntity: MovieEntity) {
        database.movieDao().insertDao(movieEntity)
    }

    override suspend fun deleteMovie(movieEntity: MovieEntity) {
        database.movieDao().deleteDao(movieEntity)
    }

    override suspend fun updateFavourite(movieEntity: MovieEntity) {
        database.movieDao().updateFavourite(movieEntity.isFavourite , movieEntity.id)
    }

    override suspend fun getAllMovies(): List<MovieEntity> {
        return database.movieDao().getAllMovies()
    }

    override suspend fun deleteMovieTable() {
        database.movieDao().nukeMovieTable()
    }
}