package com.aliakberaakash.cutiehacksproject2020.ui.features.post_details

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.aliakberaakash.cutiehacksproject2020.R
import com.aliakberaakash.cutiehacksproject2020.data.model.User
import kotlinx.android.synthetic.main.post_details_fragment.*

class PostDetailsFragment : Fragment() {

    private lateinit var viewModel: PostDetailsViewModel
    private lateinit var adapter: PostDetailsAdapter
    private val args: PostDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostDetailsViewModel::class.java)
        viewModel.getPost(args.postId)

        adapter = PostDetailsAdapter(mutableListOf())
        users_list.adapter = adapter

        viewModel.post.observe(viewLifecycleOwner, {


            if (viewModel.checkCurrentUser(it.user.email)) {
                if (it.claimers.isNullOrEmpty()) {
                    no_item_message.visibility = View.VISIBLE
                    no_item_message.text = getString(R.string.no_claimers_yet_msg)
                    draw_winner_button.isEnabled = false
                }
                draw_winner_button.text = getString(R.string.draw_winner)
                //todo call logic for draw winner
            } else {
                if (it.claimers.isNullOrEmpty())
                    no_item_message.visibility = View.VISIBLE

                if (viewModel.getCurrentUser()?.email !in it.claimers) {
                    draw_winner_button.text = getString(R.string.i_want_this)
                    //todo call logic for I want this
                } else {
                    draw_winner_button.visibility = View.GONE
                }

            }

        })

        viewModel.users.observe(viewLifecycleOwner, {
            adapter.usersList.addAll(it)
            adapter.notifyDataSetChanged()
        })


    }

}