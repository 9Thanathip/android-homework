package com.example.dominictoretto.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(context: Context) : ViewModel() {
    companion object {
        private const val PREF_NAME = "dataSave"
        private val inputText = MutableLiveData<String>()
        private var backPressedOnce = false
        private var backPressedCount = 0
    }

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    val exitApp: MutableLiveData<Boolean> = MutableLiveData()
    val isButtonVisible = MutableLiveData<Boolean>()

    fun saveData(inputText: String) {
        sharedPref.edit().apply {
            putString("key", inputText)
            apply()
        }
    }

    fun onTextChange(text: String?) {
        val textLength = text?.length ?: 0
        isButtonVisible.value = textLength >= 4
        inputText.value = text.toString()
    }

    fun onBackPressed() {
        if (backPressedCount == 1) {
            backPressedCount++
            exitApp.value = false
        } else {
            exitApp.value = true
        }
    }

}