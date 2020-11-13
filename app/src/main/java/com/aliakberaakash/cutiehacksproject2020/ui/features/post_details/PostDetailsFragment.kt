package com.aliakberaakash.cutiehacksproject2020.ui.features.post_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.aliakberaakash.cutiehacksproject2020.R
import kotlinx.android.synthetic.main.post_details_fragment.*

class PostDetailsFragment : Fragment() {

    private lateinit var viewModel: PostDetailsViewModel
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


        viewModel.post.observe(viewLifecycleOwner, {
            my_text.text = it.id
        })
    }

}