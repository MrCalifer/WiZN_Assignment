package edu.califer.wiznassignment.repository

import android.app.Application
import android.util.Log
import edu.califer.wiznassignment.BuildConfig
import edu.califer.wiznassignment.api.Models.MovieModel
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
     * Function to fetch all the trending movie of the week from TMDB.
     */
    suspend fun getTrendingMovie(): MovieModel {
        return retrofitClient.getTrendingMovie(BuildConfig.API_KEY)
    }

    /**
     * Function to fetch all the popular movie from TMDB.
     */
    suspend fun getPopularMovie(): MovieModel {
        return retrofitClient.getPopularMovie(BuildConfig.API_KEY , "en-US" , 1)
    }

}