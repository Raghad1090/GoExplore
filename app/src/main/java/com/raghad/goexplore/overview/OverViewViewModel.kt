package com.raghad.goexplore.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun displayPlacesPhotos(displayPosition: Int) {
    }

    private fun getPlacesPhotos(){

        viewModelScope.launch {
            _status.value = GoExploreStatus.LOADING
            try {

                _photos.value = GoExploreApi.retrofitService.getPhotos().photos?.photo
                Log.d("raghad", "bindRecyclerView1: $_photos")

                _title.value = _photos.value!![0]?.title
                _image.value = _photos.value!![0]?.imageUrl

                Log.e("Lamia" , "${_title.value}")

                _status.value = GoExploreStatus.DONE

            } catch (e: Exception) {
                _status.value = GoExploreStatus.ERROR
                _photos.value = listOf()

            }
        }
    }
}