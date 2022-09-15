package edu.califer.wiznassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import edu.califer.wiznassignment.R
import edu.califer.wiznassignment.databinding.FragmentDetailBinding
import edu.califer.wiznassignment.databinding.FragmentHomeBinding
import edu.califer.wiznassignment.viewmodel.ViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity.run {
            ViewModelProvider(requireActivity())[ViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.currentPosition.observe(viewLifecycleOwner){
            if (it != null && it > -1){
                val movieEntity = viewModel.movies.value!![it]
                Glide.with(binding.root)
                    .load("https://www.themoviedb.org/t/p/w220_and_h330_face/" + movieEntity.imageURL)
                    .into(binding.movieImageDetailedScreen)
                binding.movieTitle = movieEntity.title
            }
        }

        binding.previousButton.setOnClickListener {
            if (viewModel.movies.value != null && viewModel.movies.value!!.size > 0){
                if (viewModel.currentPosition.value == 0){
                    viewModel.currentPosition.value = viewModel.movies.value!!.size-1
                }
                else{
                    viewModel.currentPosition.value = viewModel.currentPosition.value!!-1
                }
            }
        }

        binding.nextButton.setOnClickListener {
            if (viewModel.movies.value != null && viewModel.movies.value!!.size > 0){
                if (viewModel.currentPosition.value == viewModel.movies.value!!.size-1){
                    viewModel.currentPosition.value = 0
                }
                else{
                    viewModel.currentPosition.value = viewModel.currentPosition.value!!+1
                }
            }
        }
    }
}