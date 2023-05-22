package com.example.dominictoretto

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dominictoretto.data.Api
import com.example.dominictoretto.data.Movie
import com.example.dominictoretto.data.loadImage
import com.example.dominictoretto.databinding.ItemMovieBinding
import com.example.dominictoretto.databinding.MoviePageHolderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: MoviePageHolderBinding
    private lateinit var movieHolder: MovieHolder
    val api = Api()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MoviePageHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPref = getSharedPreferences("dataSave", MODE_PRIVATE)
        val editor = sharedPref.edit()
        Toast.makeText(this, "ยินดีต้อนรับ", Toast.LENGTH_SHORT).show()

        binding.playBotton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.bugaboo.tv/lakorn/plerngprai")
            startActivity(intent)
        }

        binding.imageView.setOnClickListener {
            val intent = Intent(this@MovieActivity,MovieInfo::class.java)
            startActivity(intent)
        }
        ItemMovieBinding.inflate(
            layoutInflater
        )
        binding.buttonLogout.setOnClickListener {
            with(sharedPref.edit()) {
                putString("key", "")
                apply()
            }
            editor.clear()
            val intent = Intent(this@MovieActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        api.getMovieData(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val movie = response.body()?.data
                    binding.imageView.loadImage(movie?.image)
                    binding.movieName.text = movie?.title

                    movieHolder = MovieHolder()
                    binding.movieRecyclerView.apply {
                        layoutManager =
                            LinearLayoutManager(this@MovieActivity, RecyclerView.VERTICAL, false)
                        adapter = movieHolder
                    }
                    movieHolder.setList(movie?.content ?: emptyList())
                    movieHolder.notifyDataSetChanged()

                    Log.d("ddd", movie?.content.toString())
                } else {
                    Log.d("ddd", "Response not successful")
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("ddd", "API call failed: ${t.message}")
            }
        })
    }
}
