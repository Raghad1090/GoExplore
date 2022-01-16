package com.raghad.goexplore.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raghad.goexplore.R
import com.raghad.goexplore.databinding.ListItemBinding
import com.raghad.goexplore.model.FavouritesData
import com.raghad.goexplore.model.Trips


class TripsAdapter : ListAdapter<Trips, TripsAdapter.TripsViewHolder>(TripsAdapter.DiffCallback) {


    class TripsViewHolder(private var binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(favouritesData: FavouritesData) {
            Log.e("TAG", "bind: $favouritesData", )
//            binding.item = favouritesData
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Trips>() {

        override fun areItemsTheSame(oldItem: Trips, newItem: Trips): Boolean {

            return oldItem == newItem

        }

        override fun areContentsTheSame(oldItem: Trips, newItem: Trips): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripsAdapter.TripsViewHolder {
        val adapterLayout =  ListItemBinding.inflate(LayoutInflater.from(parent.context))
        return TripsAdapter.TripsViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: TripsAdapter.TripsViewHolder, position: Int) {

    }

}