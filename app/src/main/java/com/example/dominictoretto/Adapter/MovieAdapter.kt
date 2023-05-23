package com.example.dominictoretto.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.dominictoretto.Extensions.loadImage
import com.example.dominictoretto.data.infos
import com.example.dominictoretto.databinding.ItemMovieBinding

class MovieAdapter() :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var list: List<infos> = listOf()

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
            movieEpisode.isVisible = item.is_episode
            movieTitle.isVisible = !item.name.isNullOrBlank()
            movieTitle.text = item.name
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<infos>) {
        this.list = list
    }

    inner class MovieViewHolder(
        val binding: ItemMovieBinding,
        ) : RecyclerView.ViewHolder(binding.root)
}


