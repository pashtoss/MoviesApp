package com.petproject.moviesapp.presentation.screens

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.petproject.moviesapp.R
import com.petproject.moviesapp.databinding.FragmentMovieDetailsBinding
import com.petproject.moviesapp.domain.entities.Movie
import com.petproject.moviesapp.presentation.adapters.review.ReviewAdapter
import com.petproject.moviesapp.presentation.adapters.trailer.TrailerAdapter
import com.petproject.moviesapp.presentation.viewmodels.MovieDetailsViewModel
import com.petproject.moviesapp.presentation.viewmodels.MovieDetailsViewModelFactory

class MovieDetailsFragment : Fragment() {
    private lateinit var movie: Movie

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("binding is null")

    private val onTrailerClickListener: (String) -> Unit = { url ->
        val intent = Intent(Intent.ACTION_VIEW).apply { data = url.toUri() }
        startActivity(intent)
    }

    private val trailerAdapter = TrailerAdapter(onTrailerClickListener)
    private val reviewAdapter = ReviewAdapter()

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            MovieDetailsViewModelFactory(requireActivity().application, movie)
        )[MovieDetailsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMovieImage()
        binding.textViewName.text = movie.name
        binding.textViewDescription.text = movie.description
        binding.textViewYear.text = movie.year.toString()
        setOnStarClickListener()
        setObservers()
        setUpReviewAdapter()
        setUpTrailerAdapter()
    }

    private fun setObservers() {
        viewModel.isFavorite.observe(viewLifecycleOwner) {
            binding.imageViewStar.setImageResource(if (it) IMAGE_STAR_ON else IMAGE_STAR_OFF)
        }
        viewModel.reviews.observe(viewLifecycleOwner) {
            reviewAdapter.submitList(it)
        }
        viewModel.trailers.observe(viewLifecycleOwner) {
            trailerAdapter.submitList(it)
        }
    }

    private fun setOnStarClickListener() {
        binding.imageViewStar.setOnClickListener {
            viewModel.onStarPressed()
        }
    }

    private fun setUpTrailerAdapter() {
        binding.recyclerViewTrailers.adapter = trailerAdapter

    }

    private fun setUpReviewAdapter() {
        with(binding.recyclerViewReviews) {
            adapter = reviewAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    private fun setMovieImage() {
        Glide.with(requireContext())
            .load(movie.posterUrl)
            .error(R.drawable.error_image_holder)
            .into(binding.imageViewMovieImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs(): Movie {
        val args = requireArguments()
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            @Suppress("DEPRECATION") args.getParcelable<Movie>(MOVIE_KEY)
        } else {
            args.getParcelable(MOVIE_KEY, Movie::class.java)
        } ?: throw RuntimeException("no argument Movie is given")
    }

    companion object {
        fun newInstance(movie: Movie) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIE_KEY, movie)
            }
        }

        private const val IMAGE_STAR_ON = android.R.drawable.star_big_on
        private const val IMAGE_STAR_OFF = android.R.drawable.star_big_off
        private const val MOVIE_KEY = "movie"
    }
}