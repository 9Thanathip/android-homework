package com.example.dominictoretto

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dominictoretto.data.Api
import com.example.dominictoretto.data.Movie
import com.example.dominictoretto.data.MovieInfo
import com.example.dominictoretto.data.loadImage
import com.example.dominictoretto.databinding.MovieInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieInfo : AppCompatActivity() {
    private lateinit var binding:MovieInfoBinding
    val api = Api()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            backBottom.setOnClickListener{
                onBackPressed()
            }
            api.getMovieInfo(object : Callback<MovieInfo> {
                override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                    if (response.isSuccessful) {
                        val movie = response.body()?.data
                        movieImage.loadImage(movie?.image)
                        movieTitle.text = movie?.title
                        titleMain.text = movie?.title
                        typeMovie.text = movie?.type
                        typeMovie.isVisible = !movie?.type.isNullOrBlank()

                        playBotton.setOnClickListener{
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse("https://www.bugaboo.tv/lakorn/plerngprai")
                            startActivity(intent)
                        }
                        Log.d("ddd", movie?.content.toString())
                    } else {
                        Log.d("ddd", "Response not successful")
                    }
                }
                override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

            api.getDataInfo(object : Callback<MovieInfo> {
                override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                    if (response.isSuccessful) {
                        val movie = response.body()?.data
                        movieImage2.loadImage(movie?.image)
                        titleMain2.text = movie?.title
                        typeMovie2.text = movie?.type
                        typeMovie2.isVisible = !movie?.type.isNullOrBlank()

                        playBotton.setOnClickListener{
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse("https://www.bugaboo.tv/lakorn/plerngprai")
                            startActivity(intent)
                        }
                        Log.d("ddd", movie?.content.toString())
                    } else {
                        Log.d("ddd", "Response not successful")
                    }
                }
                override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}