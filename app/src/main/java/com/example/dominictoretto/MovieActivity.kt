package com.example.dominictoretto

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dominictoretto.data.Api
import com.example.dominictoretto.data.Movie
import com.example.dominictoretto.data.loadImage
import com.example.dominictoretto.databinding.MoviePageHolderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: MoviePageHolderBinding
    private lateinit var movieHolder: MovieHolder
    private lateinit var movieInfo: MovieInfo
    private val api = Api()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MoviePageHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("dataSave", MODE_PRIVATE)
        Toast.makeText(this, "ยินดีต้อนรับ", Toast.LENGTH_SHORT).show()
        setupViews()
        loadData()
    }

    private fun setupViews() {
        binding.playBotton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.bugaboo.tv/lakorn/plerngprai")
            startActivity(intent)
        }

        binding.imageView.setOnClickListener {
            val intent = Intent(this@MovieActivity, MovieInfo::class.java)
            startActivity(intent)
        }

        binding.buttonLogout.setOnClickListener {
            val sharedPref = getSharedPreferences("dataSave", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("key", "")
                apply()
            }

            val intent = Intent(this@MovieActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    fun loadData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val movie = withContext(Dispatchers.IO) {
                    Api().apiService.getData2()
                }

                val dataMovie = withContext(Dispatchers.IO) {
                    Api().apiService.getMovie2()
                }
                binding.movieName.text = dataMovie.data.title
                binding.imageView.loadImage(dataMovie.data.image)
                movieHolder = MovieHolder()
                binding.movieRecyclerView.apply {
                    layoutManager =
                        LinearLayoutManager(
                            this@MovieActivity, RecyclerView.VERTICAL,
                            false)
                    adapter = movieHolder
                }
                movieHolder.setList(dataMovie.data.content?: emptyList())
                movieHolder.notifyDataSetChanged()


                val dataInfo = withContext(Dispatchers.IO) {
                    Api().apiService.getDataInfo2()
                }

            } catch (e: Exception) {
                Log.d("ddd", e.toString())
            } finally {
//                binding.progressAction.visibility = View.GONE
            }
        }
    }
}