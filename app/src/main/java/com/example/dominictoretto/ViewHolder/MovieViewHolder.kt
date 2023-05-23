package com.example.dominictoretto.ViewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dominictoretto.Adapter.MovieAdapter
import com.example.dominictoretto.data.content
import com.example.dominictoretto.databinding.MovieViewHolderBinding

class MovieViewHolder :
    RecyclerView.Adapter<MovieViewHolder.MovieViewHolder>() {

    private var list: List<content> = listOf()

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
            movieAdapter.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun setList(list: List<content>) {
        this.list = list
    }
    inner class MovieViewHolder(
        val binding: MovieViewHolderBinding,
    ) : RecyclerView.ViewHolder(binding.root)
}