package com.example.dominictoretto

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.dominictoretto.data.Api
import com.example.dominictoretto.data.data
import com.example.dominictoretto.data.loadData
import com.example.dominictoretto.data.loadImage
import com.example.dominictoretto.databinding.MovieInfoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieInfo : AppCompatActivity() {
    private lateinit var binding:MovieInfoBinding
    private var list: List<data>? = listOf()
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
                    val movie = withContext(Dispatchers.IO) {
                        Api().apiService.getData2()
                    }
                    Log.d("ddd",movie.toString())
                    val dataInfo = withContext(Dispatchers.IO) {
                        Api().apiService.getDataInfo2()
                    }
                    Log.d("ddd",dataInfo.toString())
                    binding.apply {
                        movieImage.loadImage(movie.data.image)
                        movieTitle.text = movie.data.title
                        titleMain.text = movie.data.title
                        typeMovie.text = movie.data.type

                        movieImage2.loadImage(dataInfo.data.image)
                        titleMain2.text = dataInfo.data.title
                        typeMovie2.text = dataInfo.data.type
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