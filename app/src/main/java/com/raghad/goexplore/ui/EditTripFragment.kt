package com.raghad.goexplore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.raghad.goexplore.databinding.FragmentEditTripBinding
import com.raghad.goexplore.overview.OverViewViewModel


class EditTripFragment : Fragment() {


    private val viewModel: OverViewViewModel by viewModels()

    private var _binding: FragmentEditTripBinding? = null
    private lateinit var binding: FragmentEditTripBinding

    var Uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    lateinit var title: String

    var tripPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            tripPosition = it!!.getInt("position")

            title = it?.getString("title")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditTripBinding.inflate(inflater)
        binding = _binding!!

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}