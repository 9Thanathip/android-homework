package com.example.dominictoretto.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadMovieData(private val movieApiResponse:MovieApiResponse) {
    suspend fun getDataInfo1():MovieInfo{
        return withContext(Dispatchers.IO) {
            movieApiResponse.apiService.getData2()
        }
    }
    suspend fun getDataInfo2():MovieInfo{
        return withContext(Dispatchers.IO){
            movieApiResponse.apiService.getDataInfo2()
        }
    }
}
