package com.raghad.goexplore.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raghad.goexplore.R
import com.raghad.goexplore.databinding.GridViewItemBinding
import com.raghad.goexplore.databinding.ListItemBinding
import com.raghad.goexplore.databinding.PlanListItemBinding
import com.raghad.goexplore.model.FavouritesData
import com.raghad.goexplore.model.Trips
import com.raghad.goexplore.ui.HomeFragmentDirections
import com.raghad.goexplore.ui.TravelPlansFragmentDirections


class TripsAdapter (var onRemoveClicked:(Trips)->Unit) :
    ListAdapter<Trips, TripsAdapter.TripsViewHolder>(TripsAdapter.DiffCallback) {

    class TripsViewHolder(private var binding: PlanListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(trips: Trips) {

            binding.item = trips
            binding.executePendingBindings()
        }

        //remove button
        var removeTrip = binding.removeButton

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Trips>() {

        override fun areItemsTheSame(oldItem: Trips, newItem: Trips): Boolean {

            return oldItem.title == newItem.title

        }

        override fun areContentsTheSame(oldItem: Trips, newItem: Trips): Boolean {
            return oldItem.description == newItem.description
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TripsAdapter.TripsViewHolder {

        val adapterLayout = PlanListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TripsAdapter.TripsViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: TripsAdapter.TripsViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item)

        holder.removeTrip.setOnClickListener {

            onRemoveClicked(item)
        }
    }
}


