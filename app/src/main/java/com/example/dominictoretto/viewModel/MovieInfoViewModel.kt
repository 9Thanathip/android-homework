package com.example.dominictoretto.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dominictoretto.data.LoadMovieData
import com.example.dominictoretto.data.Movie
import com.example.dominictoretto.data.MovieInfo

class MovieInfoViewModel(private val loadMovieData: LoadMovieData) : ViewModel() {

    private val _movie = MutableLiveData<MovieInfo>()
    val movie: LiveData<MovieInfo> = _movie

    private val _movieInfo = MutableLiveData<MovieInfo>()
    val data: LiveData<MovieInfo> = _movieInfo

    private val _dataMovie = MutableLiveData<Movie>()
    val info: LiveData<Movie> = _dataMovie

    suspend fun loadData() {
        loadMovieData.loadData()
        _movie.value = loadMovieData.movie
        _movieInfo.value = loadMovieData.dataInfo
        _dataMovie.value = loadMovieData.dataMovie
    }
}