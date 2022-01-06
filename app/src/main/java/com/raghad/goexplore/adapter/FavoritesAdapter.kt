package com.raghad.goexplore.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raghad.goexplore.databinding.ListItemBinding
import com.raghad.goexplore.model.FavouritesData


class FavouritesAdapter : ListAdapter<FavouritesData, FavouritesAdapter.ItemViewHolder>(FavouritesAdapter.DiffCallback) {


    class ItemViewHolder(private var binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(favourites_data: FavouritesData) {
            binding.item = favourites_data
          binding.executePendingBindings()

    }}


    companion object DiffCallback : DiffUtil.ItemCallback<FavouritesData>() {

        override fun areItemsTheSame(oldItem: FavouritesData, newItem: FavouritesData): Boolean {

            return oldItem == newItem

        }

        override fun areContentsTheSame(oldItem: FavouritesData, newItem: FavouritesData): Boolean {
            return oldItem.imageUrl == newItem.imageUrl

        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

      //   = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val adapterLayout=  ListItemBinding.inflate(LayoutInflater.from(parent.context))
            return ItemViewHolder(adapterLayout)
    }



    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val placePhoto = getItem(position)
        holder.bind(placePhoto)
    }
}