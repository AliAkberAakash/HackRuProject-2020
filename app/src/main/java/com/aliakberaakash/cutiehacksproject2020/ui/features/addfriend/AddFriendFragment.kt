package com.aliakberaakash.cutiehacksproject2020.ui.features.addfriend

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliakberaakash.cutiehacksproject2020.R

class AddFriendFragment : Fragment() {

    companion object {
        fun newInstance() = AddFriendFragment()
    }

    private lateinit var viewModel: AddFriendViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_friend_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddFriendViewModel::class.java)
        // TODO: Use the ViewModel
    }

}