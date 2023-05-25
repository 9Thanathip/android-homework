package com.example.dominictoretto.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LogoViewModel(context: Context) : ViewModel() {
    companion object {
        private const val PREF_NAME = "dataSave"
    }

    val saveText: MutableStateFlow<String> = MutableStateFlow("")
    val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun loadSaveText() {
        saveText
        sharedPref
    }
}