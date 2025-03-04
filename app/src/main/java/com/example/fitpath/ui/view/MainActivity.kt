package com.example.fitpath.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.fitpath.R
import com.example.fitpath.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var design:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this, R.layout.activity_main)
        design.activitymainobject = this
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navhostfragment) as NavHostFragment
        NavigationUI.setupWithNavController(design.bottomnav,navHostFragment.navController)
    }
}