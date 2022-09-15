package edu.califer.wiznassignment.repository

import android.app.Application
import android.util.Log
import edu.califer.wiznassignment.BuildConfig
import edu.califer.wiznassignment.api.Models.MovieModel
import edu.califer.wiznassignment.api.Models.MovieModelItem
import edu.califer.wiznassignment.api.RetrofitClient
import edu.califer.wiznassignment.persistance.DatabaseBuilder
import edu.califer.wiznassignment.persistance.DatabaseHelperImp
import edu.califer.wiznassignment.persistance.Entities.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(application: Application) {

    private val TAG: String = "MovieRepository"

    private val dbHelper: DatabaseHelperImp =
        DatabaseHelperImp(DatabaseBuilder.getInstance(application))

    private val retrofitClient = RetrofitClient.api

    /**
     * Function to insert all the movies into the Database.
     */
    suspend fun insertMovieInDB(movieEntity: MovieEntity): MovieEntity {
        withContext(Dispatchers.IO) {
            val res = kotlin.runCatching {
                dbHelper.insertMovie(movieEntity)
            }

            res.onSuccess {
                Log.d(TAG, "Insertion Successful in Database.")
            }

            res.onFailure {
                Log.d(TAG, "Insertion Failed in Database due to ${it}.")
            }
        }
        return movieEntity
    }

    /**
     * Function to fetch all the movies from the database.
     */
    suspend fun getMoviesFromDB(): List<MovieEntity> {
        return withContext(Dispatchers.IO) {
            dbHelper.getAllMovies().map {
                MovieEntity(
                    id = it.id,
                    title = it.title,
                    isFavourite = it.isFavourite,
                    imageURL = it.imageURL,
                )
            }
        }
    }

    /**
     * Function to fetch all the trending movie of the week from TMDB.
     */
    suspend fun getTrendingMovie(): MovieModel {
        return retrofitClient.getTrendingMovie(BuildConfig.API_KEY)
    }

}