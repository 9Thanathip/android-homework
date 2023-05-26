package com.example.dominictoretto.data

import com.example.dominictoretto.viewModel.LoginViewModel
import com.example.dominictoretto.viewModel.LogoViewModel
import com.example.dominictoretto.viewModel.MovieActivityInterface
import com.example.dominictoretto.viewModel.MovieActivityViewModel
import com.example.dominictoretto.viewModel.MovieInfoInterface
import com.example.dominictoretto.viewModel.MovieInfoViewModel
import com.example.dominictoretto.viewModel.SharePreferencesImp
import com.example.dominictoretto.viewModel.SharePreferencesInterface
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { MovieApiResponse() }
    factory<MovieInfoInterface> { LoadMovieData(get()) }
    factory<MovieActivityInterface> { MovieActivityLoadData(get()) }
    single<SharePreferencesInterface> { SharePreferencesImp(get()) }

    viewModel { MovieInfoViewModel(get()) }
    viewModel { MovieActivityViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { LogoViewModel(get()) }
}