package com.example.dominictoretto.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadMovieData(private val api: Api) {
    var movie: MovieInfo? = null
    var dataMovie: Movie? = null
    var dataInfo: MovieInfo? = null

    suspend fun loadData() {
        try {
            movie = withContext(Dispatchers.IO) {
                api.apiService.getData2()
            }
            dataMovie = withContext(Dispatchers.IO) {
                api.apiService.getMovie2()
            }
            dataInfo = withContext(Dispatchers.IO) {
                api.apiService.getDataInfo2()
            }
        } catch (e: Exception) {
            Log.d("ddd", e.toString())
        }
    }
}
