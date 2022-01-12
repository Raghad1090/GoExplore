package com.raghad.goexplore

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.raghad.goexplore.overview.OverViewViewModel
import com.raghad.goexplore.ui.TravelPlansFragmentDirections
import com.raghad.goexplore.ui.TripFragmentDirections

class TripDatabase {



    private val collection = Firebase.firestore.collection("My trips")

    fun save (title: String, description: String){

        val db = Firebase.firestore

// Create a new trip
        val plan: MutableMap<String,String> = HashMap()
        plan["trip title"]  = title
        plan["trip description"]  = description


// Add a new document with a generated ID
        db.collection("My trips")
            .add(plan)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
            }
    }

}