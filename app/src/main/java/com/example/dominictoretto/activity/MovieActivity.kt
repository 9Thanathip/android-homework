package com.example.dominictoretto.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dominictoretto.R
import com.example.dominictoretto.databinding.MoviePageHolderBinding
import com.example.dominictoretto.extensions.loadImage
import com.example.dominictoretto.viewHolder.MovieViewHolder
import com.example.dominictoretto.viewModel.MovieActivityViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class MovieActivity : AppCompatActivity() {
    private lateinit var binding: MoviePageHolderBinding
    private lateinit var movieViewHolder: MovieViewHolder
    private val movieActivityViewModel: MovieActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MoviePageHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        observeData()
        movieActivityViewModel.loadData()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        movieActivityViewModel.checkCurrentTime(System.currentTimeMillis())
        Toast.makeText(this@MovieActivity, R.string.close_app, Toast.LENGTH_SHORT).show()
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

            movieViewHolder = MovieViewHolder()
            movieRecyclerView.apply {
                layoutManager = LinearLayoutManager(
                    this@MovieActivity, RecyclerView.VERTICAL, false
                )
                adapter = movieViewHolder
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeData() {
        lifecycleScope.launch {
            movieActivityViewModel.info.collect { dataMovie ->
                binding.apply {
                    movieName.text = dataMovie?.data?.title
                    imageView.loadImage(dataMovie?.data?.image)
                    movieViewHolder.setList(dataMovie?.data?.content ?: emptyList())
                    movieViewHolder.notifyDataSetChanged()
                }
            }
        }
        lifecycleScope.launch {
            movieActivityViewModel.loading.collect { isLoading ->
                binding.progressAction.isVisible = isLoading
            }
        }
        lifecycleScope.launch {
            movieActivityViewModel.backPressed.collect { isBack ->
                if (isBack) super.onBackPressed()
            }
        }
        binding.buttonLogout.setOnClickListener {
            lifecycleScope.launch {
                movieActivityViewModel.saveData("")
                val intent = Intent(this@MovieActivity, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
    }
}