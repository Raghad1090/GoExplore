package com.raghad.goexplore.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.bumptech.glide.Glide
import com.raghad.goexplore.R
import com.raghad.goexplore.model.FavouritesData
import com.raghad.goexplore.model.Trips
import com.raghad.goexplore.network.GoExploreApi
import com.raghad.goexplore.network.PhotoItem
import com.raghad.goexplore.overview.GoExploreStatus

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PhotoItem>?){

    Log.d("raghad", "bindRecyclerView: $data")

    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)

}

@BindingAdapter("favouritelistData")
fun bindfavRecyclerView(recyclerView: RecyclerView, data: List<FavouritesData>?){

    val adapter = recyclerView.adapter as FavouritesAdapter
    adapter.submitList(data)

}

@BindingAdapter("triplistData")
fun bindtripRecyclerView(recyclerView: RecyclerView, data: List<Trips>?){
    Log.d("TAG", "triplistf ${data.toString()}")
    val adapter = recyclerView.adapter as TripsAdapter
    adapter.submitList(data)

}


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl.let {
        val imgUri = imgUrl?.toUri()?.buildUpon()?.build()
        Glide.with(imgView)
            .load("${imgUri}")
            .centerCrop() // scale image to fill the entire ImageView
            .error(R.drawable.ic_connection_error)
            .placeholder(R.drawable.ic_broken_image)
            .into(imgView)
    }
}

@BindingAdapter("goExploreStatus")
fun bindStatus(statusImageView: ImageView, status: GoExploreStatus?){
    when (status) {
        GoExploreStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        GoExploreStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        GoExploreStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}