package com.raghad.goexplore.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.raghad.goexplore.R
import com.raghad.goexplore.databinding.FragmentDescriptionBinding
import com.raghad.goexplore.overview.OverViewViewModel

//private const val position = 0

class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private lateinit var binding:FragmentDescriptionBinding

    private val viewModel: OverViewViewModel by viewModels()

//    private var isFavorite = true

    var displayPosition:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {

            displayPosition = it!!.getInt("itemPosition")
            Log.d("TAG", "bindRecyclerView1: $displayPosition")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDescriptionBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("TAG", "onViewCreated: after $displayPosition ", )
        viewModel.displayDescription(displayPosition)

    }
}