package com.example.dominictoretto.viewModel

import android.content.Context
import android.provider.Settings.Global.putString
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dominictoretto.data.Movie
import com.example.dominictoretto.data.MovieActivityLoadData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieActivityViewModel(private val movieActivityLoadData: MovieActivityLoadData) :
    ViewModel() {

    companion object {
        private const val PREF_NAME = "dataSave"
    }

    private val _dataMovie = MutableStateFlow<Movie?>(null)
    val info: StateFlow<Movie?> = _dataMovie

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun loadData() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _dataMovie.value = movieActivityLoadData.getMovie2()
            } catch (e: Exception) {
                Log.d("ddd", e.toString())
            } finally {
                _loading.value = false
            }
        }
    }
    fun saveData(context: Context,inputText: String) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPref.edit().apply {
            putString("key", inputText)
            apply()
        }
    }
}