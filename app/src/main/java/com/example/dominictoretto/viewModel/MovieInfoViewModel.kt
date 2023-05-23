package com.example.dominictoretto.viewModel

import androidx.lifecycle.ViewModel
import com.example.dominictoretto.data.LoadMovieData
import com.example.dominictoretto.data.Movie
import com.example.dominictoretto.data.MovieInfo

class MovieInfoViewModel(private val loadMovieData: LoadMovieData) : ViewModel() {
    suspend fun loadData(){
        loadMovieData.loadData()
    }

    fun getMovie(): MovieInfo? = loadMovieData.movie
    fun getData(): MovieInfo? = loadMovieData.dataInfo
    fun getInfo(): Movie? = loadMovieData.dataMovie

}