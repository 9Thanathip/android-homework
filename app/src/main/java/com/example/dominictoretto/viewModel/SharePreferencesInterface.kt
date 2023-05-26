package com.example.dominictoretto.viewModel

interface SharePreferencesInterface {
    fun getString(defaultValue: String?): String?
    fun putString(value: String?)
}