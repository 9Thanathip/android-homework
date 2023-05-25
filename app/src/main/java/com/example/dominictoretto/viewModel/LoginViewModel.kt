package com.example.dominictoretto.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(context: Context) : ViewModel() {
    companion object {
        const val PREF_NAME = "dataSave"
        val inputText = MutableLiveData<String>()
    }

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
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

    suspend fun loadData() {
        withContext(Dispatchers.IO) {
            val data = sharedPref.getString("key", null)
        }
    }
}