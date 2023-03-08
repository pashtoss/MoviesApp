package com.petproject.moviesapp.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.petproject.moviesapp.R
import com.petproject.moviesapp.databinding.FragmentRootBinding
import com.petproject.moviesapp.presentation.adapters.ViewPagerAdapter

class RootFragment : Fragment() {
    private var _binding: FragmentRootBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("binding is null")

    private val childFragments = listOf(
        MoviesFragment.newInstance(),
        FavoriteMoviesFragment.newInstance()
    )
    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            childFragments
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRootBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = viewPagerAdapter
        attachTabLayoutMediator()
    }

    private fun attachTabLayoutMediator() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setIcon(
                when (position) {
                    0 -> R.drawable.image_tab_all
                    1 -> R.drawable.image_tab_favorite
                    else -> throw IllegalArgumentException("Invalid position: $position")
                }
            )
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = RootFragment()
    }
}