package com.example.dominictoretto

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dominictoretto.data.loadImage
import com.example.dominictoretto.data.LoadMovieData
import com.example.dominictoretto.databinding.MovieInfoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MovieInfo : AppCompatActivity() {
    private lateinit var binding:MovieInfoBinding
    private val loadMovieData : LoadMovieData by inject()

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
                    loadMovieData.loadData()
                    val movie = loadMovieData.movie
                    val dataInfo = loadMovieData.dataInfo

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