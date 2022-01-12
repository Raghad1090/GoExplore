package com.raghad.goexplore.data

import androidx.core.net.toUri
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ImagesUpload {

    fun uploadImageToStorage(image: String): Flow<String> = callbackFlow {

//        val storageRef = Firebase.storage.reference
//        val  storageRef = Firebase.
//
//        val scope = async {
//
//            val reference = storageRef.child
//
//            val imageUri = reference.putFile(image.toUri()).continueWithTask { task ->
//
//            if (!task.issuccessful) {
//                task.exception?.let {
//
////                    throw it
//                }
//            }
//                reference.downloadUrl
//        }.await()
//
//            return@async imageUri.toString()
//        }
//
//        trySend(scope.await())
//
//        awaitClose { }
    }
}