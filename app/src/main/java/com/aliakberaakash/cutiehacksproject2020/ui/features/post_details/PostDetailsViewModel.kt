package com.aliakberaakash.cutiehacksproject2020.ui.features.post_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliakberaakash.cutiehacksproject2020.data.model.Post
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PostDetailsViewModel : ViewModel() {
    private val db = Firebase.firestore
    val post : MutableLiveData<Post> = MutableLiveData()

    fun getPost(documentId : String){
        db.collection("posts").document(documentId)
            .get().addOnCompleteListener{
                if(it.isSuccessful){
                    post.value = it.result?.toObject(Post::class.java)
                }
            }
    }
}