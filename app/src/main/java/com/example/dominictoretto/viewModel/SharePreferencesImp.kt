package com.example.dominictoretto.viewModel

import android.content.Context
import androidx.core.content.edit

class SharePreferencesImp(context: Context) : SharePreferencesInterface {
    companion object {
        private const val PREF_NAME = "dataSave"
        private const val keyValue = "key"
    }

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun getString(defaultValue: String?): String? {
        return sharedPreferences.getString(keyValue, defaultValue)
    }

    override fun putString(value: String?) {
        sharedPreferences.edit().putString(keyValue, value).apply()
        sharedPreferences.edit {
            putString(keyValue,value)
        }
    }
}