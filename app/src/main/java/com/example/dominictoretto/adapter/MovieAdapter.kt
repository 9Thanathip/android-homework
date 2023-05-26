package com.example.dominictoretto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.dominictoretto.extensions.loadImage
import com.example.dominictoretto.data.Info
import com.example.dominictoretto.databinding.ItemMovieBinding

class MovieAdapter() :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var list: List<Info> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            movieImage.loadImage(item.image)
            movieEpisode.isVisible = item.isEpisode
            movieTitle.isVisible = !item.name.isNullOrBlank()
            movieTitle.text = item.name
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<Info>) {
        this.list = list
    }

    inner class MovieViewHolder(
        val binding: ItemMovieBinding,
        ) : RecyclerView.ViewHolder(binding.root)
}



