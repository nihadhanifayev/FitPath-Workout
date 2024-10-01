package com.example.fitpath

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.fitpath.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var design:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = ActivityMainBinding.inflate(layoutInflater)
        setContentView(design.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navhostfragment) as NavHostFragment

        NavigationUI.setupWithNavController(design.bottomnav,navHostFragment.navController)

        design.floatingActionButton.setOnClickListener {
            val intent = Intent(this,ActivityTraining::class.java)

            startActivity(intent)
        }

    }
}