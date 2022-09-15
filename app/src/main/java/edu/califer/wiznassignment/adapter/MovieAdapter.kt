package edu.califer.wiznassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.califer.wiznassignment.R
import edu.califer.wiznassignment.databinding.MovieRecyclerItemBinding
import edu.califer.wiznassignment.persistance.Entities.MovieEntity

class MovieAdapter(private val movies: ArrayList<MovieEntity> , var movieListener: MovieListener) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(private val binding: MovieRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieEntity: MovieEntity) {
            Glide.with(binding.root)
                .load("https://www.themoviedb.org/t/p/w220_and_h330_face/" + movieEntity.imageURL)
                .into(binding.movieImage)
            binding.movieName = movieEntity.title
            if (movieEntity.isFavourite) {
                binding.favMovieButton.setImageResource(R.drawable.ic_favorite)
            } else {
                binding.favMovieButton.setImageResource(R.drawable.ic_not_favorite)
            }
        }

        fun onClick(
            movieEntity: MovieEntity,
            holder: ViewHolder,
            movieAdapter: MovieAdapter,
            position: Int,
            movieListener: MovieListener
        ) {
            binding.favMovieButton.setOnClickListener {
                movieEntity.isFavourite = !movieEntity.isFavourite
                movieListener.onFavourite(movieEntity , position)
                if (movieEntity.isFavourite) {
                    holder.binding.favMovieButton.setImageResource(R.drawable.ic_favorite)
                } else {
                    holder.binding.favMovieButton.setImageResource(R.drawable.ic_not_favorite)
                }
            }

            binding.deleteMovieButton.setOnClickListener {
                movieAdapter.movies.remove(movieEntity)
                movieListener.onDelete(movieEntity , position)
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
        holder.bind(movies[position])
        holder.onClick(movies[position], holder, this, position , movieListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    interface MovieListener{
        fun onDelete(movieEntity: MovieEntity, position: Int)
        fun onFavourite(movieEntity: MovieEntity, position: Int)
    }
}