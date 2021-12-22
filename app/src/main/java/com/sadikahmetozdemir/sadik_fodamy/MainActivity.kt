package com.sadikahmetozdemir.sadik_fodamy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> bottomNavigationView.visibility = View.GONE
                R.id.loginFragment -> bottomNavigationView.visibility = View.GONE
                R.id.signUpFragment -> bottomNavigationView.visibility = View.GONE
                R.id.introFragment->bottomNavigationView.visibility=View.GONE
                R.id.forgotPassword->bottomNavigationView.visibility=View.GONE
                R.id.recipeImageFragment->bottomNavigationView.visibility=View.GONE


                else -> bottomNavigationView.visibility=View.VISIBLE

            }


        }


    }


}