package edu.califer.wiznassignment.api

import edu.califer.wiznassignment.api.Models.MovieModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {


    @GET("trending/movie/week")
    suspend fun getTrendingMovie(@Query("api_key") api_key: String) : MovieModel

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieModel

}