package com.example.dominictoretto.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dominictoretto.data.LoadMovieData
import com.example.dominictoretto.data.Movie
import com.example.dominictoretto.data.MovieInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieInfoViewModel(private val loadMovieData: LoadMovieData) : ViewModel() {

    private val _movie = MutableStateFlow(MovieInfo())
    val movie: StateFlow<MovieInfo> = _movie

    private val _movieInfo = MutableStateFlow(MovieInfo())
    val data: StateFlow<MovieInfo> = _movieInfo

    private val _dataMovie = MutableStateFlow(Movie())
    val info: StateFlow<Movie> = _dataMovie

    suspend fun loadData() {
        loadMovieData.loadData()
        _movie.value = loadMovieData.movie ?: MovieInfo()
        _movieInfo.value = loadMovieData.dataInfo ?: MovieInfo()
        _dataMovie.value = loadMovieData.dataMovie ?: Movie()
    }
}