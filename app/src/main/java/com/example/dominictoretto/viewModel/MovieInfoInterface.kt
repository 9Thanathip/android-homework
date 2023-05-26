package com.example.dominictoretto.viewModel

import com.example.dominictoretto.data.MovieInfo

interface MovieInfoInterface {

    suspend fun getDataInfo1():MovieInfo

    suspend fun getDataInfo2():MovieInfo

}