package com.raghad.goexplore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.raghad.goexplore.R
import com.raghad.goexplore.databinding.FragmentTravelPlansBinding
import com.raghad.goexplore.databinding.FragmentTripBinding

/*
app trip plan list page
 */

class TravelPlansFragment : Fragment() {

    private var _binding: FragmentTravelPlansBinding? = null
    private lateinit var binding: FragmentTravelPlansBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTravelPlansBinding.inflate(inflater)
        binding = _binding!!
        // Inflate the layout for this fragment
        binding =  FragmentTravelPlansBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.floatingActionButton?.setOnClickListener{

            var action = TravelPlansFragmentDirections.actionTravelPlansFragmentToTripFragment()
            findNavController().navigate(action)

        }
    }


}