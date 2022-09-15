package edu.califer.wiznassignment.repository

import android.app.Application
import android.util.Log
import edu.califer.wiznassignment.persistance.DatabaseBuilder
import edu.califer.wiznassignment.persistance.DatabaseHelper
import edu.califer.wiznassignment.persistance.DatabaseHelperImp
import edu.califer.wiznassignment.persistance.Entities.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(application: Application)  {

    private val TAG :String = "MovieRepository"

    private val dbHelper : DatabaseHelperImp = DatabaseHelperImp(DatabaseBuilder.getInstance(application))

    suspend fun insertMovieInDB(movieEntity: MovieEntity): MovieEntity {
        withContext(Dispatchers.IO){
            val res = kotlin.runCatching {
                dbHelper.insertMovie(movieEntity)
            }

            res.onSuccess {
                Log.d(TAG , "Insertion Successful in Database.")
            }

            res.onFailure {
                Log.d(TAG , "Insertion Failed in Database due to ${it}.")
            }
        }

        return movieEntity
    }

}