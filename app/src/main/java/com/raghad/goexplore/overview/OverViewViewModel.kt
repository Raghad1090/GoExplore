package com.raghad.goexplore.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.raghad.goexplore.model.FavouritesData
import com.raghad.goexplore.network.GoExploreApi
import com.raghad.goexplore.network.PhotoItem
import kotlinx.coroutines.launch
import java.lang.Exception

enum class GoExploreStatus {LOADING, ERROR, DONE}

class OverViewViewModel : ViewModel(){


    private val _status = MutableLiveData<GoExploreStatus>()
    val status: LiveData<GoExploreStatus> = _status

    private val _photos = MutableLiveData<List<PhotoItem?>>()
    val photos: LiveData<List<PhotoItem?>> = _photos

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image


    init {

        getPlacesPhotos()
    }


    private fun getPlacesPhotos(){

        viewModelScope.launch {
            _status.value = GoExploreStatus.LOADING
            try {
                _photos.value = GoExploreApi.retrofitService.getPhotos().photos?.photo
                Log.d("rrr", "bindRecyclerView1: $_photos")

                _title.value = _photos.value!![0]?.title
                _image.value = _photos.value!![0]?.imageUrl

                _status.value = GoExploreStatus.DONE

            } catch (e: Exception) {
                _status.value = GoExploreStatus.ERROR
                _photos.value = listOf()

            }
        }
    }


    fun displayDescription(displayPosition: Int){

        val item = _photos.value?.last()
        Log.d("TAG", "bindRecyclerView1: $displayPosition")
        _image.value = item?.imageUrl
        _title.value = item?.title

    }


//    fun favourite(index : Int ) : FavouritesData {
//      var item = _photos.value?.get(index)
//
//        _image.value = item?.imageUrl
//        _title.value = item?.title
//
//        return FavouritesData( _title.value )
//    }
}