package edu.califer.wiznassignment.api

import edu.califer.wiznassignment.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    val api: APIServices = retrofit.build().create(APIServices::class.java)
}