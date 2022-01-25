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
import com.raghad.goexplore.model.Trips
import com.raghad.goexplore.network.GoExploreApi
import com.raghad.goexplore.network.PhotoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

enum class GoExploreStatus { LOADING, ERROR, DONE }

class OverViewViewModel : ViewModel() {


    var Uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    // collection to save trip plan
    private val tripsCollection = Firebase.firestore.collection("My trips")

    //collection to save favourite items
    private val favouriteCollection = Firebase.firestore.collection("Favourite")

    private val _status = MutableLiveData<GoExploreStatus>()
    val status: LiveData<GoExploreStatus> = _status

    //region app lists
    private val _photos = MutableLiveData<List<PhotoItem?>>()
    val photos: LiveData<List<PhotoItem?>> = _photos

    private val _fav = MutableLiveData<MutableList<FavouritesData?>>()
    val fav: LiveData<MutableList<FavouritesData?>> = _fav

    private val _plans = MutableLiveData<MutableList<Trips?>>()
    val plans: LiveData<MutableList<Trips?>> = _plans

    //endregion

    //region viewModel variables
    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image

    private val _tTitle = MutableLiveData<String>()
    val tTitle: LiveData<String> = _tTitle

    private val _tDescription = MutableLiveData<String>()
    val tDescription: LiveData<String> = _tDescription

    private val _tDestination = MutableLiveData<String>()
    val tDestination: LiveData<String> = _tDestination

    private var _isLoaded = MutableStateFlow<Boolean>(false)

    //endregion

    //region display images list in home page

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

    //endregion

    //region display place image and description
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

    //endregion

    //region favourites
    fun addFavourite(id: String, imageId: String) {

        val favItem = _photos.value?.find {

            Log.e("TAG", "addFavourite: id:  ${it!!.id} imageid ${imageId}")

            it!!.id == imageId
        }

        favItem.let {

            val favItem: FavouritesData = FavouritesData(it?.title, it?.id, it?.imageUrl)

            addFavouriteToFirebace(favItem)

        }
    }

    /*
    * save data to firestore database
    * */
    fun addFavouriteToFirebace(item: FavouritesData) {

        favouriteCollection.document("user").collection(Uid).add(item)
            .addOnCompleteListener { task ->

            }.addOnFailureListener { println(it.message) }
    }


    fun getFavourite() {

        favouriteCollection.document("user").collection(Uid).get()

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


    fun removeFavourite(item: FavouritesData){

        favouriteCollection.document("user").collection(Uid)

            .whereEqualTo("title", item.title)
            .whereEqualTo("imageUrl", item.imageUrl)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (task.result!!.documents.isNotEmpty()) {
                        for (data in task.result!!.documents) {
                        favouriteCollection.document("user").collection(Uid)
                        .document(data.id).delete()
                            getFavourite()
                    }
                }
            }
        }
    }

    fun checkIfFavourite(imageId: String): Boolean {

        viewModelScope.launch {
            getFavourite()
        }

        var checkFav = _fav.value?.find {

            it!!.id == imageId
        }

        checkFav.let { return true }

        return false
    }

    //endregion


    //region trips

    fun saveTrip (title: String, description: String , destination:String){

        val trip = Trips(title,description,destination)

        tripsCollection.document("user").collection(Uid).add(trip)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
            }
    }

    fun getTrip() {

        tripsCollection.document("user").collection(Uid).get()

            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val item = mutableListOf<Trips?>()

                    for (data in task.result.documents) {

                        Log.d("tripData","trip: ${task.result.documents}")

                        val trip = data.toObject<Trips>()
                        item.add(trip!!)
                    }

                    _plans.value = item
                    Log.d("tripListData","trip: ${item}")
                }

            }.addOnFailureListener { println(it.message) }
    }



    fun removeTrip(item: Trips){

        tripsCollection.document("user").collection(Uid)

            .whereEqualTo("title", item.title)
            .whereEqualTo("destination", item.destinations)
            .whereEqualTo("description", item.description)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (task.result!!.documents.isNotEmpty()) {
                        for (data in task.result!!.documents) {
                            tripsCollection.document("user").collection(Uid)
                                .document(data.id).delete()
                            getTrip()
                    }
                }
            }
        }
    }

    //endregion
}