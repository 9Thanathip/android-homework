package com.example.dominictoretto.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieActivityLoadData(private val api: Api) {
    suspend fun getMovie2():Movie {
        return withContext(Dispatchers.IO) {
            api.apiService.getMovie2()
        }
    }
}