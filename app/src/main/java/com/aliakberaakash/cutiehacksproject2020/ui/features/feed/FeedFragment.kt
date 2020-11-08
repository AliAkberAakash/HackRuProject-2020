package com.aliakberaakash.cutiehacksproject2020.ui.features.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.aliakberaakash.cutiehacksproject2020.R
import com.aliakberaakash.cutiehacksproject2020.data.model.Post
import com.aliakberaakash.cutiehacksproject2020.data.model.User
import com.aliakberaakash.cutiehacksproject2020.ui.features.addfriend.SendPicFragment
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.feed_fragment.*

class FeedFragment : Fragment() {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        val user = Firebase.auth.currentUser

        var postList:MutableList<Post> = mutableListOf()
        val db = Firebase.firestore
        db.collection("posts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    val id:String = document.getString("id")!!
                    val description:String = document.getString("description")?:""
                    val image:String = document.getString("image")!!
                    postList.add(Post(id, User(
                        id = user!!.email!!,
                        userName = user.displayName!!,
                        image = ""
                    ),description,image))
                }
            }

        val adapter = PostAdapter(listOf(
            Post(
                image = "",
                id = "",
                user = User(
                    id = user!!.email!!,
                    userName = user.displayName!!,
                    image = ""
                ),
                description = ""
            ),
                    Post(
                    image = "",
            id = "",
            user = User(
                id = user!!.email!!,
                userName = user.displayName!!,
                image = ""
            ),
            description = ""
        )

        ))
        Log.d("xoxo", postList.toString())

        feed_recyclerview.adapter = adapter

    }

}