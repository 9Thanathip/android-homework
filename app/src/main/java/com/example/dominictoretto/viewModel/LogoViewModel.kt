package com.example.dominictoretto.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogoViewModel(context: Context) : ViewModel() {
    companion object {
        private const val PREF_NAME = "dataSave"
    }

    private val _checkLogin = MutableStateFlow<Boolean?>(null)
    val checkLogin: StateFlow<Boolean?> = _checkLogin

    fun loadSaveText(context: Context) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        viewModelScope.launch {
            delay(3000)
            _checkLogin.value = sharedPref.getString("key", "").isNullOrBlank()
        }
    }
}