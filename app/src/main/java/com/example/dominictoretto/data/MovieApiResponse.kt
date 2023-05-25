package com.example.dominictoretto.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiResponse {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(MovieService::class.java)
}