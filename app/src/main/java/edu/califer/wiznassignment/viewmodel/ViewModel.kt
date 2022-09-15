package edu.califer.wiznassignment.viewmodel

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.califer.wiznassignment.R
import edu.califer.wiznassignment.persistance.Entities.MovieEntity
import edu.califer.wiznassignment.repository.MovieRepository
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    private val TAG: String = "ViewModel"
    lateinit var application: Application

    var movies: MutableLiveData<ArrayList<MovieEntity>> = MutableLiveData<ArrayList<MovieEntity>>()

    /**
     * Function to Launch Fragment
     * @param fragment Pass the fragment to be launched
     * @param tag Pass the tag of the fragment
     */
    fun launchFragment(fragment: Fragment, tag: String, fragmentManager: FragmentManager) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /**
     * Function to fetch trending movies of the week from TMDB.
     */
    private fun fetchTrendingMovie() {
        Log.d(TAG, "API called.")
        val movieRepository = MovieRepository(application)
        viewModelScope.launch {
            val result = kotlin.runCatching {
                movieRepository.getTrendingMovie()
            }

            result.onSuccess { it ->
                Log.d(TAG, "Response item size : ${it.results.size}")
                it.results.forEach {
                    movieRepository.insertMovieInDB(
                        MovieEntity(
                            it.id.toString(),
                            it.title,
                            false,
                            it.poster_path
                        )
                    )
                }
                fetchTrendingMovieFromDB()
            }

            result.onFailure {
                Log.e(TAG, "Failed due to $it")
            }
        }
    }

    /**
     * Function to fetch trending movies of the week from Database.
     */
    fun fetchTrendingMovieFromDB() {
        val movieRepository = MovieRepository(application)
        viewModelScope.launch {
            val result = kotlin.runCatching {
                movieRepository.getMoviesFromDB()
            }

            result.onSuccess {
                if (it.isEmpty()) {
                    fetchTrendingMovie()
                } else {
                    movies.value = it as ArrayList<MovieEntity>
                }
                Log.d(TAG, "DB Items size : ${it.size}")
            }

            result.onFailure {
                Log.e(TAG, "Failed due to $it")
            }
        }
    }

    /**
     * Function to update the favorite movie in Database
     */
    fun updateFavouriteMovie(movieEntity: MovieEntity){
        val movieRepository = MovieRepository(application)
        viewModelScope.launch {
            val result = kotlin.runCatching {
                movieRepository.updateFavouriteMovie(movieEntity)
            }

            result.onSuccess {
                Log.d(TAG , "Item Updated successfully in DB.")
            }

            result.onFailure {
                Log.e(TAG , "Item failed to updated in DB due to $it.")
            }
        }
    }

    /**
     * Function to delete a movie from the database.
     */
    fun deleteMovie(movieEntity: MovieEntity){
        val movieRepository = MovieRepository(application)
        viewModelScope.launch {
            val result = kotlin.runCatching {
                movieRepository.deleteMovie(movieEntity)
            }

            result.onSuccess {
                Log.d(TAG , "Item Deleted successfully from DB.")
            }

            result.onFailure {
                Log.e(TAG , "Item failed to delete from DB due to $it.")
            }
        }
    }

    /**For the Status Bar Icon Color.
     * 0-> Icon Color will be White
     * else->Icon Color will be Black.*/
    fun statusBarIconColor(colorCode: Int, activity: Activity) {
        activity.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }
        when (colorCode) {
            0 -> {
                activity.window.apply {
                    decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                }
            }
            else -> {
                activity.window.apply {
                    decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }
        }
    }

}