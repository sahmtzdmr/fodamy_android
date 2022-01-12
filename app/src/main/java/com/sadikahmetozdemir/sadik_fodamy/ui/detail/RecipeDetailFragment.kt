package com.sadikahmetozdemir.sadik_fodamy.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentRecipeDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeDetailFragment :
    BaseFragment<FragmentRecipeDetailBinding, RecipeDetailViewModel>(R.layout.fragment_recipe_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener("request_unfollow") { _, bundle ->
            if (bundle.getBoolean("unfollow", false)) {
                viewModel.userUnfollow()
            }
        }
    }
}


