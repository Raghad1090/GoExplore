package com.raghad.goexplore.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raghad.goexplore.databinding.PlanListItemBinding
import com.raghad.goexplore.model.Trips
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
        //edit button
        var editTrip = binding.topic

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


    override fun onBindViewHolder(holder: TripsViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item)

        holder.removeTrip.setOnClickListener {

            onRemoveClicked(item)
        }

        holder.editTrip.setOnClickListener {

            val action = TravelPlansFragmentDirections.actionTravelPlansFragmentToEditTripFragment(title = "title")
            holder.editTrip.findNavController().navigate(action)
        }
    }
}



