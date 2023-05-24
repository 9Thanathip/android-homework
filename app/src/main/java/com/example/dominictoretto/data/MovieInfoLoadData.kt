package com.example.dominictoretto.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadMovieData(private val api:Api) {
    suspend fun getDataInfo1():MovieInfo{
        return withContext(Dispatchers.IO) {
            api.apiService.getData2()
        }
    }
    suspend fun getDataInfo2():MovieInfo{
        return withContext(Dispatchers.IO){
            api.apiService.getDataInfo2()
        }
    }
}
