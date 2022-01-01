package com.raghad.goexplore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raghad.goexplore.Database
import com.raghad.goexplore.R
import com.raghad.goexplore.databinding.FragmentFavoritesBinding
import com.raghad.goexplore.databinding.FragmentHomeBinding

/*
app favouriate list page
 */

class FavoritesFragment : Fragment() {

    private var binding: FragmentFavoritesBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    val fragmentFavoritesBinding = FragmentFavoritesBinding.inflate(inflater,container,false)
        binding = fragmentFavoritesBinding
        return fragmentFavoritesBinding.root
         }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    companion object {
    }
}