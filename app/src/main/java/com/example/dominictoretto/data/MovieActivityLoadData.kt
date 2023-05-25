package com.example.dominictoretto.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieActivityLoadData(private val movieApiResponse: MovieApiResponse) {
    suspend fun getMovie2():Movie {
        return withContext(Dispatchers.IO) {
            movieApiResponse.apiService.getMovieData()
        }
    }
}