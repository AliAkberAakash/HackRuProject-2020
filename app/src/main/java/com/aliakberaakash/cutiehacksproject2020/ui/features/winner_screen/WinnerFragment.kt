package com.aliakberaakash.cutiehacksproject2020.ui.features.winner_screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliakberaakash.cutiehacksproject2020.R

class WinnerFragment : Fragment() {

    companion object {
        fun newInstance() = WinnerFragment()
    }

    private lateinit var viewModel: WinnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.winner_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WinnerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}