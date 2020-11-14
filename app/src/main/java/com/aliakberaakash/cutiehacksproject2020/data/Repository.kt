package com.aliakberaakash.cutiehacksproject2020.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Repository {

    val user = Firebase.auth.currentUser
    fun checkCurrentUser(email : String) = email == user?.email
    fun getCurrentUser() = user
}