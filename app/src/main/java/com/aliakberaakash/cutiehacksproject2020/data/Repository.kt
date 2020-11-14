package com.aliakberaakash.cutiehacksproject2020.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Repository {

    val user = Firebase.auth.currentUser
    private val db = Firebase.firestore

    fun checkCurrentUser(email : String) = email == user?.email

    fun getCurrentUser() = user

    fun onIWantThisClicked(documentId : String){
        db.collection("posts")
            .document(documentId)
            .update("claimers", FieldValue.arrayUnion(user?.email))
    }

    fun onCancelClaimClicked(documentId: String) {
        db.collection("posts")
            .document(documentId)
            .update("claimers", FieldValue.arrayRemove(user?.email))
    }
}