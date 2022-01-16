package com.raghad.goexplore.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.raghad.goexplore.model.FavouritesData
import com.raghad.goexplore.network.GoExploreApi
import com.raghad.goexplore.network.PhotoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update

enum class GoExploreStatus { LOADING, ERROR, DONE }

class OverViewViewModel : ViewModel() {

    var Uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    // collection to save trip plan
    private val collection = Firebase.firestore.collection("My trips")

    //collection to save favourite items
    private val favourateCollection = Firebase.firestore.collection("Favouriate")


    private val _status = MutableLiveData<GoExploreStatus>()
    val status: LiveData<GoExploreStatus> = _status

    private val _photos = MutableLiveData<List<PhotoItem?>>()
    val photos: LiveData<List<PhotoItem?>> = _photos

    private val _fav = MutableLiveData<MutableList<FavouritesData?>>()
    val fav: LiveData<MutableList<FavouritesData?>> = _fav

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image

    private val _tripT = MutableLiveData<String>()
    var tripTitle: LiveData<String> = _tripT

    private val _tripD = MutableLiveData<String>()
    var tripD: LiveData<String> = _tripD

    private var _isLoaded = MutableStateFlow<Boolean>(false)


    private val favouritesDataFirebace = Firebase.firestore.collection("Favouriate")


    init {
        getPlacesPhotos()
    }


    private fun getPlacesPhotos() {

        viewModelScope.launch {
            _status.value = GoExploreStatus.LOADING
            try {
                _photos.value = GoExploreApi.retrofitService.getPhotos().photos?.photo!!
                Log.d("rrr", "bindRecyclerView1: ${_photos.value.toString()}")

                _isLoaded.update { true }
                _status.value = GoExploreStatus.DONE

            } catch (e: Exception) {
                _status.value = GoExploreStatus.ERROR
                _photos.value = listOf()
            }
        }
    }

    fun getItem(postion: Int) {
        viewModelScope.launch {
            _isLoaded.collect {
                val item = _photos.value?.get(postion)
                item?.let {
                    _image.value = it.imageUrl
                    _title.value = it.title
                }
            }
        }
    }

    fun displayDescription(position: Int, imageID: String) {

        getItem(position)
    }


    fun addFavouriate(id: String, imageId: String) {

        val favItem = _photos.value?.find {

            Log.e("TAG", "addFavouriate: id:  ${it!!.id} imageid ${imageId}")

            it!!.id == imageId
        }

//        val favItem1 = _fav.value?.find { it!!.id == imageId.toString() }

        favItem.let {

            Log.e("TAG", "addFavouriate: title ${it?.title}")
            Log.e("TAG", "addFavouriate: farm ${it?.farm}")
            Log.e("TAG", "addFavouriate:  secret ${it?.secret}")
            Log.e("TAG", "addFavouriate: server ${it?.server}")
            Log.e("TAG", "addFavouriate: imageUrl ${it?.imageUrl}")
            val favItem: FavouritesData =
                FavouritesData(it?.title, it?.id,  it?.imageUrl)

            addFavouriateToFirebace(favItem)

        }
    }

    fun addFavouriateToFirebace(item: FavouritesData) {

        favourateCollection.document("user").collection(Uid).add(item)
            .addOnCompleteListener { task ->

            }.addOnFailureListener { println(it.message) }
    }


    fun getFavouriate() {

        favourateCollection.document("user").collection(Uid).get()

            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val item = mutableListOf<FavouritesData?>()

                    for (data in task.result.documents) {

                        val favouriate = data.toObject<FavouritesData>()
                        item.add(favouriate!!)
                    }
                    _fav.value = item
                }

            }.addOnFailureListener { println(it.message) }
    }

    fun displayTrip() {
        Log.e("TAG", "showUserInfo: in")
        var doc = collection.document(Uid)
        Log.e("TAG", "showUserInfo: doc $doc")
        doc.get()
            .addOnCompleteListener { task ->
                Log.e("TAG", "showUserInfo:  1 ")
                _tripT.value = task.result.data?.get("trip title").toString()
                _tripD.value = task.result.data?.get("trip description").toString()
            }.addOnFailureListener { println(it.message) }
    }
}