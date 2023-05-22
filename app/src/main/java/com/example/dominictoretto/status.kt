package com.example.dominictoretto

import com.google.gson.annotations.SerializedName

data class statusHeader(
    @SerializedName("status")
    val status: MutableList<String> = mutableListOf()
)