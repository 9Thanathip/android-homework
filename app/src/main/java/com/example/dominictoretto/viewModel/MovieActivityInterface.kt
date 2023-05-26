package com.example.dominictoretto.viewModel

import com.example.dominictoretto.data.Movie

interface MovieActivityInterface {
    suspend fun getMovie2(): Movie

}