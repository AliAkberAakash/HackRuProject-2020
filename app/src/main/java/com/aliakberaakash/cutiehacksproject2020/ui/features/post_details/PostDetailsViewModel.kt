package com.aliakberaakash.cutiehacksproject2020.ui.features.post_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliakberaakash.cutiehacksproject2020.data.model.Post
import com.aliakberaakash.cutiehacksproject2020.data.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PostDetailsViewModel : ViewModel() {
    private val db = Firebase.firestore
    val post : MutableLiveData<Post> = MutableLiveData()
    val users : MutableLiveData<List<User>> = MutableLiveData()

    fun getPost(documentId : String){
        db.collection("posts").document(documentId)
            .get().addOnCompleteListener{
                if(it.isSuccessful){
                    val post = it.result?.toObject(Post::class.java)
                    val userList = post?.claimers
                    getUsers(userList)
                }
            }
    }

    private fun getUsers(usersList : List<String>?){
        if(usersList.isNullOrEmpty())
            return

        db.collection("users")
                .whereIn("email", usersList)
                .get()
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        users.value = it.result?.toObjects(User::class.java)
                    }
                }

    }
}