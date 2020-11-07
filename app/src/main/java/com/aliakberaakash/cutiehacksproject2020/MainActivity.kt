package com.aliakberaakash.cutiehacksproject2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.aliakberaakash.cutiehacksproject2020.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navHostFragment : NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpDataBinding()
        setUpNavigation()
    }

    private fun setUpDataBinding(){
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
    }

    private fun setUpNavigation(){
        navHostFragment = (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment?)!!
        NavigationUI.setupWithNavController(binding.bottomNav,
            navHostFragment.navController
        )
    }
}