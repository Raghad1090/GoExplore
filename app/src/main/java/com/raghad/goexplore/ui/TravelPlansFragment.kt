package com.raghad.goexplore.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.raghad.goexplore.R
import com.raghad.goexplore.adapter.PhotoGridAdapter
import com.raghad.goexplore.adapter.TripsAdapter
import com.raghad.goexplore.databinding.FragmentHomeBinding
import com.raghad.goexplore.databinding.FragmentTravelPlansBinding
import com.raghad.goexplore.databinding.FragmentTripBinding
import com.raghad.goexplore.model.Trips

/*
app trip plan list page
 */

class TravelPlansFragment : Fragment() {

    private var binding: FragmentTravelPlansBinding? = null
    private lateinit var _binding: FragmentTravelPlansBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentTravelPlansBinding.inflate(inflater)

        binding.recyclerViewTrips.adapter = TripsAdapter()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.floatingActionButton?.setOnClickListener{

            var action = TravelPlansFragmentDirections.actionTravelPlansFragmentToTripFragment()
            view.findNavController().navigate(action)

        }

    }
}