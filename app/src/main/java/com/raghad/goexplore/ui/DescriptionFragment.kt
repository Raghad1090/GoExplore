package com.raghad.goexplore.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.raghad.goexplore.databinding.FragmentDescriptionBinding
import com.raghad.goexplore.overview.OverViewViewModel

/*
place discription page
 */
class DescriptionFragment : Fragment() {

    private val viewModel: OverViewViewModel by viewModels()

    private var _binding: FragmentDescriptionBinding? = null
    private lateinit var binding: FragmentDescriptionBinding

    var Uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    //to get arg values
    var displayPosition: Int = 0

    lateinit var imageID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {

            displayPosition = it!!.getInt("itemPosition")

            imageID = it?.getString("imageId")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDescriptionBinding.inflate(inflater)
        binding = _binding!!

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.displayDescription(displayPosition, imageID)

        binding.favButton.setOnClickListener {

            viewModel.addFavourite(Uid, imageID)
        }
    }
}