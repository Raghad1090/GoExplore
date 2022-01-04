package com.raghad.goexplore

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Database {

    fun save (title:String){
        val db = Firebase.firestore
// Create a new trip
       val plans: MutableMap<String,String> = HashMap()
        plans["trip title"]  = title


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

        db.collection("My trips")
            .get()
            .addOnCompleteListener {

                val result: StringBuffer = StringBuffer()
                if (it.isSuccessful) {

                    for (document in it.result!!) {
                        result.append(document.data.getValue("trip title")).append("")
                    }
                }
//                    .addOnFailureListener { exception ->
//                        Log.w(TAG, "Error getting documents.", exception)
//                }
            }
    }
}