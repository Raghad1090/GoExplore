package com.raghad.goexplore

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Database {

    fun save(title:String){
        val db = Firebase.firestore
// Create a new user with a first and last name
       val plans: MutableMap<String,String> = HashMap()
        plans["trip"]  = title



// Add a new document with a generated ID
        db.collection("users")
            .add(plans)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

}