package com.raghad.goexplore.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.raghad.goexplore.databinding.FragmentTripBinding
import com.raghad.goexplore.overview.OverViewViewModel

class TripFragment : Fragment() {

    private val viewModel: OverViewViewModel by viewModels()

    private var binding: FragmentTripBinding? = null

//    var id = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentTripBinding = FragmentTripBinding.inflate(inflater, container, false)
        binding = fragmentTripBinding

        return fragmentTripBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.plans.observe(viewLifecycleOwner, {
//
//            Log.d("TAG", "onViewCreated: ${it.toString()}")
//        })
        //click to save trip in Trips list
        binding?.creatTrip?.setOnClickListener {

            //send input data to save function
            viewModel.save(
                binding!!.addTitle.text.toString(),
                binding!!.addDes.text.toString(),
                binding!!.addDestination.text.toString()
            )

            //back to plans page
            val action = TripFragmentDirections.actionTripFragmentToTravelPlansFragment()
            view.findNavController().navigate(action)
        }
    }
}

