package com.petproject.moviesapp.presentation.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.petproject.moviesapp.R
import com.petproject.moviesapp.databinding.FragmentFavoriteMoviesBinding
import com.petproject.moviesapp.domain.MovieDetailsMode
import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.presentation.adapters.movie.ItemMarginDecoration
import com.petproject.moviesapp.presentation.adapters.movie.MovieAdapter
import com.petproject.moviesapp.presentation.viewmodels.FavoriteMoviesViewModel

class FavoriteMoviesFragment : Fragment() {
    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("binding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[FavoriteMoviesViewModel::class.java]
    }

    private val onItemClickListener: (Movie) -> Unit = {
        launchFavoriteDetailsFragment(it)
    }
    private val movieAdapter = MovieAdapter(onItemClickListener)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.favoriteMovies.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.textViewEmptyListMessage.visibility = View.VISIBLE
            } else {
                binding.textViewEmptyListMessage.visibility = View.GONE
                movieAdapter.submitList(it)
            }
        }
    }
    private fun Context.dpToPx(dp: Int): Int {
        return (dp.toFloat() * this.resources.displayMetrics.density).toInt()
    }

    private fun setUpRecyclerView() {
        binding.recyclerViewMoviesList.adapter = movieAdapter
        binding.recyclerViewMoviesList.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.recyclerViewMoviesList.addItemDecoration(
            ItemMarginDecoration(
                requireContext().dpToPx(ITEM_MARGIN)
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchFavoriteDetailsFragment(movie: Movie) {
        val detailsFragment = MovieDetailsFragment.newInstance(
            movie,
            MovieDetailsMode.MODE_FAVORITES
        )
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.mainContainer, detailsFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = FavoriteMoviesFragment()
        private const val ITEM_MARGIN = 3
    }
}