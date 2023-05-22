package com.example.dominictoretto.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieApi = retrofit.create(Interface::class.java)

    fun getMovieData(callback: Callback<Movie>) {
        val call = movieApi.getMovie()
        call.enqueue(callback)
    }

    fun getMovieInfo(callback: Callback<MovieInfo>) {
        val call = movieApi.getData()
        call.enqueue(callback)
    }

    fun getDataInfo(callback: Callback<MovieInfo>) {
        val call = movieApi.getDataInfo()
        call.enqueue(callback)
    }
}