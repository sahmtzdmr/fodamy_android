package com.sadikahmetozdemir.sadik_fodamy.ui.intro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FirstWalkThroughAdapter(list:ArrayList<Fragment>,fm:FragmentManager,lifecycle:Lifecycle):FragmentStateAdapter(fm,lifecycle) {
   private val fragmentList : ArrayList<Fragment> = list


    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}