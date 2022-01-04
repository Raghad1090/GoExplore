package com.raghad.goexplore.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Appendable

class TripViewModel(application: Application): AndroidViewModel(application) {

//    private val readAllData: LiveData<List<Trip>>
//    private val repository: TripRepository
//
//    init {
//        val dao = TripDatabase.getDatabase(application).dao()
//        repository = TripRepository(dao)
//        readAllData = repository.readAllData
//    }
//
//    fun addTrip(trip: Trip){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addTtrip(trip)
//        }
//    }
}