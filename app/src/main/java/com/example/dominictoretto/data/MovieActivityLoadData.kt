package com.example.dominictoretto.data

import com.example.dominictoretto.viewModel.MovieActivityInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieActivityLoadData(private val movieApiResponse: MovieApiResponse) :
    MovieActivityInterface {
    override suspend fun getMovie2(): Movie {
        return withContext(Dispatchers.IO) {
            movieApiResponse.apiService.getMovieData()
        }
    }
}