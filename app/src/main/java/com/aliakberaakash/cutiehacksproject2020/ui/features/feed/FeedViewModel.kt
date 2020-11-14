package com.aliakberaakash.cutiehacksproject2020.ui.features.feed

import androidx.lifecycle.ViewModel
import com.aliakberaakash.cutiehacksproject2020.data.Repository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FeedViewModel : ViewModel() {

    private val db = Firebase.firestore
    val user = Firebase.auth.currentUser
    private val repository = Repository()

    fun checkCurrentUser(email : String) = repository.checkCurrentUser(email)

    fun getCurrentUser() = repository.getCurrentUser()

    fun onIWantThisClicked(documentId : String){
        db.collection("posts")
            .document(documentId)
            .update("claimers", FieldValue.arrayUnion(user?.email))
    }

    fun onCancelClaimClicked(documentId : String){
        db.collection("posts")
            .document(documentId)
            .update("claimers", FieldValue.arrayRemove(user?.email))
    }

}