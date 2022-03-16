package com.sadikahmetozdemir.sadik_fodamy.ui.add_recipe

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentPostRecipeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostRecipeFragment :
    BaseFragment<FragmentPostRecipeBinding, PostRecipeViewModel>(R.layout.fragment_post_recipe) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    fun initObservers() {
        viewModel.numberOfRecipes.observe(viewLifecycleOwner) { itList ->
//            val list= arrayListOf<String>()
//            itList.forEach {
//                it.text?.let { it1 -> list.add(it1) }
//            }
            binding.actvNumberOfPeople.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    itList
                )
            )
        }
    }
}
