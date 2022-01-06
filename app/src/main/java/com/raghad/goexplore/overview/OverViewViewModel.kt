package com.raghad.goexplore.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.raghad.goexplore.model.FavouritesData
import com.raghad.goexplore.network.GoExploreApi
import com.raghad.goexplore.network.PhotoItem
import com.raghad.goexplore.network.Photos
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

    private val favouritesDataFirebace = Firebase.firestore.collection("user")

    init {
        getPlacesPhotos(0)
    }


    private fun getPlacesPhotos(withIndex:Int){

        viewModelScope.launch {
            _status.value = GoExploreStatus.LOADING
            try {
                _photos.value = GoExploreApi.retrofitService.getPhotos().photos?.photo
                Log.d("rrr", "bindRecyclerView1: ${_photos.value.toString()}")
                val item = _photos.value?.get(withIndex)

                _image.value = item?.imageUrl
                _title.value = item?.title

                _status.value = GoExploreStatus.DONE

            } catch (e: Exception) {
                _status.value = GoExploreStatus.ERROR
                _photos.value = listOf()

            }
        }
    }


    fun displayDescription(displayPosition: Int){

        getPlacesPhotos(displayPosition)


    }


    fun favourite(index:Int, user: String) : FavouritesData{

        var item = _photos.value?.get(index)

        _image.value = item?.imageUrl

        return FavouritesData(_image.value)
    }


    fun addToFirebace(item: FavouritesData){

        var user = FirebaseAuth.getInstance().currentUser?.uid?:""

        favouritesDataFirebace.document(user).update("fav",item)
            .addOnCompleteListener{

            }
        }
    }
