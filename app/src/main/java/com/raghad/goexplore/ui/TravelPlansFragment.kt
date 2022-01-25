package com.raghad.goexplore.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.raghad.goexplore.R
import com.raghad.goexplore.adapter.TripsAdapter
import com.raghad.goexplore.databinding.FragmentTravelPlansBinding
import com.raghad.goexplore.model.FavouritesData
import com.raghad.goexplore.overview.OverViewViewModel

/*
wish list page
 */
class TravelPlansFragment : Fragment() {

    private val viewModel: OverViewViewModel by viewModels()

    private lateinit var binding: FragmentTravelPlansBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(layoutInflater,R.layout.fragment_travel_plans,container,false)

        binding.lifecycleOwner=viewLifecycleOwner

        binding.viewModel=viewModel

        binding.recyclerViewTrips.adapter = TripsAdapter{viewModel.removeTrip(it)}

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingButton.setOnClickListener {

            val action = TravelPlansFragmentDirections.actionTravelPlansFragmentToTripFragment()
            findNavController().navigate(action)
        }

        viewModel.getTrip()
    }
}