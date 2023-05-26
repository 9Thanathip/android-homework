package com.example.dominictoretto.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dominictoretto.data.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieActivityViewModel(private val movieActivityInterface: MovieActivityInterface) :
    ViewModel() {

    companion object {
        private const val PREF_NAME = "dataSave"
    }

    var backPressedTime: Long = 0

    private val _dataMovie = MutableStateFlow<Movie?>(null)
    val info: StateFlow<Movie?> = _dataMovie

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _backPressed = MutableStateFlow(false)
    val backPressed: MutableStateFlow<Boolean> = _backPressed

    fun loadData() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _dataMovie.value = movieActivityInterface.getMovie2()
            } catch (e: Exception) {
                Log.d("ddd", e.toString())
            } finally {
                _loading.value = false
            }
        }
    }

    fun saveData(context: Context, inputText: String) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPref.edit().apply {
            putString("key", inputText)
            apply()
        }
    }

    fun checkCurrentTime(time: Long) {
        if (time - backPressedTime < 2000) {
            _backPressed.value = true
        } else {
            backPressedTime = time
        }
    }
}