package com.example.dominictoretto

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dominictoretto.data.loadImage
import com.example.dominictoretto.data.LoadMovieData
import com.example.dominictoretto.databinding.MovieInfoBinding
import com.example.dominictoretto.viewModel.MovieInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieInfo : AppCompatActivity() {
    private lateinit var binding:MovieInfoBinding
    private val movieInfoViewModel: MovieInfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            backBottom.setOnClickListener {
                onBackPressed()
            }

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    movieInfoViewModel.loadData()
                    val movie = movieInfoViewModel.getMovie()
                    val dataInfo = movieInfoViewModel.getData()

                    binding.apply {
                        movie?.apply {
                            movieImage.loadImage(data.image)
                            movieTitle.text = data.title
                            titleMain.text = data.title
                            typeMovie.text = data.type
                        }
                        dataInfo?.apply {
                            movieImage2.loadImage(data.image)
                            titleMain2.text = data.title
                            typeMovie2.text = data.type
                        }
                    }

                } catch (e: Exception) {
                    Log.d("ddd", e.toString())
                } finally {
                    progressAction.visibility = View.GONE
                }
            }

        }
    }
}