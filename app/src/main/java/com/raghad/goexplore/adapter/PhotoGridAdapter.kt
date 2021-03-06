package com.raghad.goexplore.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raghad.goexplore.databinding.GridViewItemBinding
import com.raghad.goexplore.network.PhotoItem
import com.raghad.goexplore.ui.HomeFragmentDirections

class PhotoGridAdapter : ListAdapter<PhotoItem, PhotoGridAdapter.GoExplorePhotoViewHolder>(DiffCallback) {


    class GoExplorePhotoViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photoItem: PhotoItem) {
            binding.photo = photoItem
            binding.executePendingBindings()
            Log.d("photo", "bindRecyclerView: $photoItem")

        }

        //click on photo to open description page
        var displayDesscripton = binding.placeImage
    }


    companion object DiffCallback : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {

            return oldItem.title == newItem.title

        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.imageUrl == newItem.imageUrl

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoExplorePhotoViewHolder {

        return GoExplorePhotoViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: GoExplorePhotoViewHolder, position: Int) {

        val placePhoto = getItem(position)
        holder.bind(placePhoto)

        holder.displayDesscripton.setOnClickListener {

            var positionArg = placePhoto.id

            val action = HomeFragmentDirections.actionHomeFragmentToDescriptionFragment(position,positionArg!!)
            holder.displayDesscripton.findNavController().navigate(action)

        }

    }
}





