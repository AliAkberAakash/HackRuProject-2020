package com.aliakberaakash.cutiehacksproject2020.ui.features.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aliakberaakash.cutiehacksproject2020.R
import com.aliakberaakash.cutiehacksproject2020.data.model.Post
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import timber.log.Timber

class PostAdapter(var postList: List<Post>, val callback: FeedFragmentCallback) : RecyclerView.Adapter<PostViewHolder>() {

    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        context = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_post_item, parent, false)
        return PostViewHolder(view)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        Timber.d(postList[position].image)

        Glide
            .with(context)
            .load(postList[position].image)
            .dontAnimate()
            .placeholder(R.drawable.ic_loading)
            .into(holder.postImage)

        holder.userName.text = postList[position].user.userName
        holder.description.text = postList[position].description

        holder.postContainer.setOnClickListener {

            val action = FeedFragmentDirections.actionFeedFragmentToPostDetailsFragment(
                postId = postList[position].id
            )
            it.findNavController().navigate(action)
        }

        if(callback.checkCurrentUser(postList[position].user.email)) {
            holder.iWantThisButton.visibility = View.GONE
            holder.cancelButton.visibility = View.GONE
        }else {
            if(callback.getCurrentUserEmail() in  postList[position].claimers)
            {
                holder.iWantThisButton.visibility = View.GONE
                holder.cancelButton.visibility = View.VISIBLE
            }else{
                holder.iWantThisButton.visibility = View.VISIBLE
                holder.cancelButton.visibility = View.GONE
            }

        }

        holder.iWantThisButton.setOnClickListener {
            val x = holder.iWantThisButton.visibility
            holder.iWantThisButton.visibility = holder.cancelButton.visibility
            holder.cancelButton.visibility = x
            callback.onIWantThisClicked(postList[position].id)
        }

        holder.cancelButton.setOnClickListener {
            val x = holder.iWantThisButton.visibility
            holder.iWantThisButton.visibility = holder.cancelButton.visibility
            holder.cancelButton.visibility = x
            callback.onCancelClaimClicked(postList[position].id)
        }

        if(postList[position].description.isEmpty())
            holder.description.visibility = View.GONE

    }

    override fun getItemCount() = postList.size
}