package com.example.dominictoretto.data

import com.example.dominictoretto.viewModel.MovieInfoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadMovieData(private val movieApiResponse: MovieApiResponse) : MovieInfoInterface {
    override suspend fun getDataInfo1(): MovieInfo {
        return withContext(Dispatchers.IO) {
            movieApiResponse.apiService.getDataInfoFirst()
        }
    }

    override suspend fun getDataInfo2(): MovieInfo {
        return withContext(Dispatchers.IO) {
            movieApiResponse.apiService.getDataInfoSecond()
        }
    }
}
