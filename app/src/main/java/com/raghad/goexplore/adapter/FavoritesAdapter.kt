package com.raghad.goexplore.adapter

import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raghad.goexplore.R
import com.raghad.goexplore.model.FavouritesData
import com.raghad.goexplore.databinding.ListItemBinding

class FavouritesAdapter(var onRemoveClicked:(FavouritesData)->Unit): ListAdapter<FavouritesData, FavouritesAdapter.ItemViewHolder>(FavouritesAdapter.DiffCallback ) {


    class ItemViewHolder(private var binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(favouritesData: FavouritesData) {

            binding.item = favouritesData
            binding.executePendingBindings()
        }

        //remove button
        var removeFav = binding.remove
    }


    companion object DiffCallback : DiffUtil.ItemCallback<FavouritesData>() {

        override fun areItemsTheSame(oldItem: FavouritesData, newItem: FavouritesData): Boolean {

            return oldItem.title == newItem.title

        }

        override fun areContentsTheSame(oldItem: FavouritesData, newItem: FavouritesData): Boolean {
            return oldItem.imageUrl== newItem.imageUrl
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val adapterLayout = ListItemBinding.inflate(LayoutInflater.from(parent.context))
            return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val favItem = getItem(position)
        holder.bind(favItem)

        holder.removeFav.setOnClickListener{

            onRemoveClicked(favItem)
        }
    }
}