package com.example.dominictoretto.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.dominictoretto.Extensions.loadImage
import com.example.dominictoretto.databinding.MovieInfoBinding
import com.example.dominictoretto.viewModel.MovieInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
        loadData()
        binding.apply {
            backBottom.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun observeData() {

        movieInfoViewModel.movie.observe(this@MovieInfoActivity) { movie ->
            binding.apply {
                movieImage.loadImage(movie.data?.image)
                titleMain.text = movie.data?.title
                typeMovie.text = movie.data?.type
                movieTitle.text = movie.data?.title
            }
        }

        movieInfoViewModel.data.observe(this@MovieInfoActivity) { dataInfo ->
            binding.apply {
                movieImage2.loadImage(dataInfo.data?.image)
                titleMain2.text = dataInfo.data?.title
                typeMovie2.text = dataInfo.data?.type
            }
        }
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                movieInfoViewModel.loadData()
            } catch (e: Exception) {
                Log.d("ddd", e.toString())
            } finally {
                binding.progressAction.isVisible = false
            }
        }
    }
}