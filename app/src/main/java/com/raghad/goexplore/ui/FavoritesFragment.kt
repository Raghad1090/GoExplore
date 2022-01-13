package com.raghad.goexplore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import com.raghad.goexplore.databinding.FragmentFavoritesBinding
import com.raghad.goexplore.overview.OverViewViewModel



class FavoritesFragment : Fragment() {

    private val viewModel: OverViewViewModel by viewModels()

    private var _binding: FragmentFavoritesBinding? = null

    private lateinit var binding: FragmentFavoritesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFavoritesBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

    //    binding.recyclerViewFavouriate.adapter = FavouritesAdapter()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}