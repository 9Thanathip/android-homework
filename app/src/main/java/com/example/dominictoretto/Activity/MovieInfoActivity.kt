package com.example.dominictoretto.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.dominictoretto.Extensions.loadImage
import com.example.dominictoretto.databinding.MovieInfoBinding
import com.example.dominictoretto.viewModel.MovieInfoViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieInfoActivity : AppCompatActivity() {
    private lateinit var binding: MovieInfoBinding
    private val movieInfoViewModel: MovieInfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()
        movieInfoViewModel.loadData()
        binding.apply {
            backBottom.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            movieInfoViewModel.data.collect { movie ->
                binding.apply {
                    movieImage.loadImage(movie.data?.image)
                    titleMain.text = movie.data?.title
                    typeMovie.text = movie.data?.type
                    movieTitle.text = movie.data?.title
                }
            }
        }

        lifecycleScope.launch {
            movieInfoViewModel.info.collect { dataInfo ->
                binding.apply {
                    movieImage2.loadImage(dataInfo.data?.image)
                    titleMain2.text = dataInfo.data?.title
                    typeMovie2.text = dataInfo.data?.type
                }
            }
        }
        lifecycleScope.launch {
            movieInfoViewModel.loading.collect { isLoading ->
                binding.progressAction.isVisible = isLoading
            }
        }
    }
}