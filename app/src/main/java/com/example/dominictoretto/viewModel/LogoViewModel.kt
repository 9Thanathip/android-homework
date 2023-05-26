package com.example.dominictoretto.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogoViewModel(private val sharePreferencesInterface: SharePreferencesInterface) :
    ViewModel() {
    companion object {
        private const val PREF_NAME = "dataSave"
    }

    private val _checkLogin = MutableStateFlow<Boolean?>(null)
    val checkLogin: StateFlow<Boolean?> = _checkLogin

    fun loadSaveText() {
        viewModelScope.launch {
            delay(3000)
            _checkLogin.value = sharePreferencesInterface.getString("").isNullOrBlank()
        }
    }
}