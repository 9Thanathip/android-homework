package com.example.dominictoretto.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel(
    context: Context,
    private val sharePreferencesInterface: SharePreferencesInterface
) : ViewModel() {
    companion object {
        private const val PREF_NAME = "dataSave"
    }

    private var inputData: String? = null
    private var backPressedTime: Long = 0
    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val _isButtonVisible = MutableStateFlow(false)
    val isButtonVisible: MutableStateFlow<Boolean> = _isButtonVisible

    private val _backPressed = MutableStateFlow(false)
    val backPressed: MutableStateFlow<Boolean> = _backPressed

    fun onTextChange(text: String?) {
        inputData = text.toString()
        val textLength = text?.length ?: 0
        if (textLength >= 4) {
            isButtonVisible.value = true
        }
    }

    fun onClick() {
        sharePreferencesInterface.putString(inputData)
    }

    fun checkCurrentTime(time: Long) {
        if (time - backPressedTime < 2000) {
            _backPressed.value = true
        } else {
            backPressedTime = time
        }
    }
}