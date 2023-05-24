package com.example.dominictoretto.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dominictoretto.data.LoadMovieData
import com.example.dominictoretto.data.MovieInfo
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieInfoViewModel(private val loadMovieData: LoadMovieData) : ViewModel() {

    private val _movieInfo = MutableStateFlow(MovieInfo())
    val data: StateFlow<MovieInfo> = _movieInfo

    private val _dataMovie = MutableStateFlow(MovieInfo())
    val info: StateFlow<MovieInfo> = _dataMovie

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun loadData() {
        viewModelScope.launch {
            try {
                _loading.value = true
                val movieDeferred = async { loadMovieData.getDataInfo1() }
                val dataMovieDeferred = async { loadMovieData.getDataInfo2() }

                val movie = movieDeferred.await()
                val dataMovie = dataMovieDeferred.await()

                _movieInfo.value = movie
                _dataMovie.value = dataMovie
            } finally {
                _loading.value = false
            }
        }
    }
}