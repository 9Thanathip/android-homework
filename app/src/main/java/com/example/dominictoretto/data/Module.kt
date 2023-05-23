package com.example.dominictoretto.data

import org.koin.dsl.module

val appModule = module {
    factory { LoadMovieData(get()) }
}