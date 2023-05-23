package com.example.dominictoretto.data

import com.example.dominictoretto.viewModel.MovieInfoViewModel
import org.koin.dsl.module

val appModule = module {
    factory { Api() }
    factory { LoadMovieData(get()) }
    factory { MovieInfoViewModel(get()) }
}