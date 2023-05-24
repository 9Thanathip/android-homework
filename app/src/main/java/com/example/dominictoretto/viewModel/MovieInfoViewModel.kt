package com.example.dominictoretto.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dominictoretto.data.LoadMovieData
import com.example.dominictoretto.data.MovieInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieInfoViewModel(private val loadMovieData: LoadMovieData) : ViewModel() {

    private val _movieInfo = MutableStateFlow(MovieInfo())
    val data: StateFlow<MovieInfo> = _movieInfo

    private val _dataMovie = MutableStateFlow(MovieInfo())
    val info: StateFlow<MovieInfo> = _dataMovie

    fun loadData() {
        viewModelScope.launch {
            try {
                _movieInfo.value = loadMovieData.getDataInfo1()
                _dataMovie.value = loadMovieData.getDataInfo2()
            } catch (e: Exception) {
            }
        }
    }

}