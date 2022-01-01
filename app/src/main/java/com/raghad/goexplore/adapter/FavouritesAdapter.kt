package com.raghad.goexplore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Tasks
import com.raghad.goexplore.R
import com.raghad.goexplore.model.FavouritesData

class FavouritesAdapter ( private val context: Context,
private val dataset: List<FavouritesData>)
: RecyclerView.Adapter<FavouritesAdapter.ItemViewHolder>() {

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val placeName: TextView = view.findViewById(R.id.destination)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //hold data

        val item = dataset[position]

        holder.placeName.text = item.title
    }

    override fun getItemCount() = dataset.size

    }

