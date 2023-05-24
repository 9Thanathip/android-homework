package com.example.dominictoretto.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dominictoretto.Extensions.loadImage
import com.example.dominictoretto.ViewHolder.MovieViewHolder
import com.example.dominictoretto.data.Movie
import com.example.dominictoretto.databinding.MoviePageHolderBinding
import com.example.dominictoretto.viewModel.MovieActivityViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: MoviePageHolderBinding
    private lateinit var movieViewHolder: MovieViewHolder
    private var list: List<Movie> = listOf()
    private val movieActivityViewModel: MovieActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MoviePageHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupViews()
        Log.d("ddd","list - "+list)
    }

    private fun setupViews() {
        binding.apply {
            playBotton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.bugaboo.tv/lakorn/plerngprai")
                startActivity(intent)
            }

            imageView.setOnClickListener {
                val intent = Intent(this@MovieActivity, MovieInfoActivity::class.java)
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

    @SuppressLint("NotifyDataSetChanged")
    private fun observeData() {
        lifecycleScope.launch {
            movieActivityViewModel.info.collect { dataMovie ->
                binding.apply {
                    dataMovie.apply {
                        movieName.text = dataMovie.data?.title
                        imageView.loadImage(dataMovie.data?.image)
                        movieViewHolder = MovieViewHolder()
                        movieRecyclerView.apply {
                            layoutManager =
                                LinearLayoutManager(
                                    this@MovieActivity, RecyclerView.VERTICAL,
                                    false
                                )
                            adapter = movieViewHolder
                        }
                        movieViewHolder.setList(dataMovie.data?.content ?: emptyList())
                        movieViewHolder.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            try {
                movieActivityViewModel.loadData()
                observeData()
            } catch (e: Exception) {
                Log.d("ddd", e.toString())
            } finally {
                binding.scrollView.isVisible = true
            }
        }
    }
}