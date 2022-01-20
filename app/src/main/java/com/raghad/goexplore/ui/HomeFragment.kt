package com.raghad.goexplore.ui

import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.raghad.goexplore.R
import com.raghad.goexplore.adapter.PhotoGridAdapter
import com.raghad.goexplore.databinding.FragmentHomeBinding
import com.raghad.goexplore.overview.OverViewViewModel

/*
app home page
 */
class HomeFragment : Fragment() {

    private val viewModel: OverViewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.photosGrid.adapter = PhotoGridAdapter()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
