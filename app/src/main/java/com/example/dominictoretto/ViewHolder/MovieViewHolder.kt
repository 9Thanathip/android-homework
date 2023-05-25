package com.example.dominictoretto.ViewHolder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dominictoretto.Adapter.MovieAdapter
import com.example.dominictoretto.data.Content
import com.example.dominictoretto.databinding.MovieViewHolderBinding

class MovieViewHolder :
    RecyclerView.Adapter<MovieViewHolder.MovieViewHolder>() {

    private var list: List<Content> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = MovieViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            titleName.text = item.title

            val movieAdapter = MovieAdapter()
            movieRecycler.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = movieAdapter
            }
            item.infos?.let { movieAdapter.setList(it) }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Content>) {
        this.list = list
        notifyDataSetChanged()
    }
    inner class MovieViewHolder(
        val binding: MovieViewHolderBinding,
    ) : RecyclerView.ViewHolder(binding.root)
}