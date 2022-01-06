package com.raghad.goexplore

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TripDatabase {

    fun save (title: String, description: String){

        val db = Firebase.firestore

// Create a new trip
        val plans: MutableMap<String,String> = HashMap()
        plans["trip title"]  = title
        plans["trip description"]  = description


// Add a new document with a generated ID
        db.collection("My trips")
            .add(plans)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun read (){

        val db = Firebase.firestore

       val readData = db.collection("My trips").document("user")
        readData.get()
            .addOnCompleteListener {document ->

                if (document != null) {

                } else {
                }
            }
            .addOnFailureListener { exception ->
            }
    }
}