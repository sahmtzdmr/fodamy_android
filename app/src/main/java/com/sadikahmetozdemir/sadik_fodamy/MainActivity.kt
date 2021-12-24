package com.sadikahmetozdemir.sadik_fodamy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.gone
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.visible
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
           if (shouldShowBottomNav(destination.id)){
                bottomNavigationView.visible()
           }
            else
                bottomNavigationView.gone()


        }


    }
    private fun shouldShowBottomNav(id: Int):Boolean{
        return listOf(R.id.homeTablayoutFragment,R.id.favoritesCategoriesFragment).contains(id)



    }


}