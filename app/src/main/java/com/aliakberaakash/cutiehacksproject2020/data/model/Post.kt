package com.aliakberaakash.cutiehacksproject2020.data.model
import com.google.firebase.auth.FirebaseUser

data class Post (
    val id : Int,
    val user : FirebaseUser,
    val description : String,
    val image : String,
)