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
        viewModel.numberOfRecipes.observe(viewLifecycleOwner) { itNumber ->
            binding.actvNumberOfPeople.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    itNumber
                )
            )
        }
        viewModel.timeOfRecipes.observe(viewLifecycleOwner) { itTime ->
            binding.actvTimeOfRecipe.setAdapter(
                ArrayAdapter(
                    requireContext(), android.R.layout.simple_spinner_dropdown_item, itTime
                )
            )

        }

    }
}
