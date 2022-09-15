package edu.califer.wiznassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.califer.wiznassignment.R
import edu.califer.wiznassignment.databinding.MovieRecyclerItemBinding
import edu.califer.wiznassignment.persistance.Entities.MovieEntity

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(private val binding: MovieRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieEntity: MovieEntity) {
            Glide.with(binding.root).load(movieEntity.imageURL).into(binding.movieImage)
            binding.movieName = movieEntity.title
            if (movieEntity.isFavourite) {
                binding.favMovieButton.setImageResource(R.drawable.ic_favorite)
            } else {
                binding.favMovieButton.setImageResource(R.drawable.ic_not_favorite)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MovieRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}