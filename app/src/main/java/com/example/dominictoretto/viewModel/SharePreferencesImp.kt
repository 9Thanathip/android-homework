package com.example.dominictoretto.viewModel

import android.content.Context

class SharePreferencesImp(context: Context) : SharePreferencesInterface {
    companion object {
        private const val PREF_NAME = "dataSave"
    }

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override suspend fun getString(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    override suspend fun putString(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }
}