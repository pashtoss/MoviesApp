package com.petproject.moviesapp.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.petproject.moviesapp.R
import com.petproject.moviesapp.databinding.FragmentMovieListBinding
import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.presentation.adapters.movie.MovieAdapter
import com.petproject.moviesapp.presentation.viewmodels.MovieListViewModel

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("binding is null")
    private val viewModel by lazy { ViewModelProvider(this)[MovieListViewModel::class.java] }

    private val onItemClickListener: (Movie) -> Unit = {
        launchDetailsFragment(it)
    }
    private val onScrollEndListener: () -> Unit = {
        viewModel.loadMovies()
    }
    private val movieAdapter = MovieAdapter(onItemClickListener, onScrollEndListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.currentMovieList.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBarLoading.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerViewMoviesList.adapter = movieAdapter
        binding.recyclerViewMoviesList.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchDetailsFragment(movie: Movie) {
        val detailsFragment = MovieDetailsFragment.newInstance(movie)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, detailsFragment)
            .addToBackStack(null)
            .commit()
    }
}