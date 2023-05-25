package com.example.dominictoretto.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel(context: Context) : ViewModel() {
    companion object {
        private const val PREF_NAME = "dataSave"
        private val inputText = MutableLiveData<Boolean>(false)
        var backPressedTime: Long = 0
    }

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    val isButtonVisible = MutableLiveData<Boolean>()

    private val _backPressed = MutableStateFlow(false)
    val backPressed: MutableStateFlow<Boolean> = _backPressed

    fun onTextChange(text: String?) {
        val textLength = text?.length ?: 0
        isButtonVisible.value = textLength >= 4
        inputText.value = textLength > 4
        sharedPref.edit().apply {
            putString("key", text)
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