package com.sadikahmetozdemir.sadik_fodamy.ui.image

import android.os.Bundle
import android.view.View
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentRecipeImagePopUpBinding

class RecipeImagePopUpFragment :
    BaseFragment<FragmentRecipeImagePopUpBinding, RecipeImageViewModel>(
        R.layout.fragment_recipe_image_pop_up
    ) {
    private var args: RecipeImagePopUpFragmentArgs? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            args = RecipeImagePopUpFragmentArgs.fromBundle(it)
        }
        args?.editorChoiseModel?.let {

            val urlList = arrayListOf<String>()

            it.images?.get(0)?.url?.let { it1 ->
                urlList.add(it1)
                urlList.add(it1)
            }

            binding.imageViewpager2.adapter = RecipeImageAdapter(urlList)
            binding.imageViewpager2.let { it.let { it1 -> binding.indicator.setViewPager2(it1) } }
        }
    }
}
