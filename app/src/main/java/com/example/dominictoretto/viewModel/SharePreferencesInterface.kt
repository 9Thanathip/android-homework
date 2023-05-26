package com.example.dominictoretto.viewModel

interface SharePreferencesInterface {
    suspend fun getString(key: String, defaultValue: String?): String?
    suspend fun putString(key: String, value: String?)
}