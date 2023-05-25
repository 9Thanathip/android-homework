package com.example.dominictoretto.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel

class LogoViewModel(context: Context) : ViewModel() {
    companion object {
        private const val PREF_NAME = "dataSave"
    }

    fun loadSaveText(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString("key", "").isNullOrBlank()
    }
}