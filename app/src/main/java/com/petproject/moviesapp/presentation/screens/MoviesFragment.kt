package com.petproject.moviesapp.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.petproject.moviesapp.R
import com.petproject.moviesapp.databinding.FragmentMoviesBinding
import com.petproject.moviesapp.domain.MovieDetailsMode
import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.presentation.adapters.movie.MovieAdapter
import com.petproject.moviesapp.presentation.viewmodels.MoviesViewModel

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("binding is null")
    private val viewModel by lazy { ViewModelProvider(this)[MoviesViewModel::class.java] }

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
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.currentMovieList.observe(viewLifecycleOwner) { newList ->
            movieAdapter.submitList(newList)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBarLoading.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerViewMoviesList.adapter = movieAdapter
        binding.recyclerViewMoviesList.layoutManager =
            GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchDetailsFragment(movie: Movie) {
        val detailsFragment = MovieDetailsFragment.newInstance(
            movie,
            MovieDetailsMode.MODE_GENERAL
        )
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.mainContainer, detailsFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }
}