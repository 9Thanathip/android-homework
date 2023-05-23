package com.example.dominictoretto

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dominictoretto.data.LoadMovieData
import com.example.dominictoretto.data.loadImage
import com.example.dominictoretto.databinding.MoviePageHolderBinding
import com.example.dominictoretto.viewModel.MovieInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: MoviePageHolderBinding
    private lateinit var movieHolder: MovieHolder
    private val movieInfoViewModel: MovieInfoViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MoviePageHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupViews()
    }

    private fun setupViews() {
        binding.apply {
            playBotton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.bugaboo.tv/lakorn/plerngprai")
                startActivity(intent)
            }

            imageView.setOnClickListener {
                val intent = Intent(this@MovieActivity, MovieInfo::class.java)
                startActivity(intent)
            }

            buttonLogout.setOnClickListener {
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
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                movieInfoViewModel.loadData()
                val dataMovie = movieInfoViewModel.getInfo()
                binding.apply {
                    dataMovie?.apply {
                        movieName.text = data.title
                        imageView.loadImage(data.image)
                        movieHolder = MovieHolder()
                        movieRecyclerView.apply {
                            layoutManager =
                                LinearLayoutManager(
                                    this@MovieActivity, RecyclerView.VERTICAL,
                                    false
                                )
                            adapter = movieHolder
                        }
                        movieHolder.setList(data.content ?: emptyList())
                        movieHolder.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {
                Log.d("ddd", e.toString())
            } finally {
                binding.progressAction.isVisible = false
                binding.scrollView.isVisible = true
            }
        }
    }
}