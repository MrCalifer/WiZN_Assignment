package edu.califer.wiznassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import edu.califer.wiznassignment.R
import edu.califer.wiznassignment.adapter.MovieAdapter
import edu.califer.wiznassignment.databinding.FragmentHomeBinding
import edu.califer.wiznassignment.viewmodel.ViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }


    override fun onResume() {
        super.onResume()

        binding.movieRecyclerView.apply {
            layoutManager =LinearLayoutManager(context)
            adapter = MovieAdapter()
        }
    }
}