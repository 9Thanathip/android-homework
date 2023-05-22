package com.example.dominictoretto.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface Interface {
    @GET("/v3/eb3b3684-3875-4af3-8b8f-c5d97b2840ce")
    fun getMovie(): Call<Movie>

    @GET("/v3/87befd54-5e8b-4f7f-8ad8-3110e1991b57")
    fun getData():Call<MovieInfo>

    @GET("/v3/941cfefe-66a1-47f5-af71-960d43200e32")
    fun getDataInfo():Call<MovieInfo>
}