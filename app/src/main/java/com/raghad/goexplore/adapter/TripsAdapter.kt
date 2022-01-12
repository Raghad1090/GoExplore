package com.raghad.goexplore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raghad.goexplore.R
import com.raghad.goexplore.model.Trips

class TripsAdapter(private val tripsList: ArrayList<Trips>): RecyclerView.Adapter<TripsAdapter.TripsViewHolder>() {

    public class TripsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

//        val destination: TextView = itemView.findViewById(R.id.destination)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TripsAdapter.TripsViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.plan_list_item, parent,false)

        return TripsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TripsAdapter.TripsViewHolder, position: Int) {

        val trip : Trips = tripsList[position]
//        holder.destination.text = trip.description


    }

    override fun getItemCount(): Int {
        return tripsList.size
    }
}