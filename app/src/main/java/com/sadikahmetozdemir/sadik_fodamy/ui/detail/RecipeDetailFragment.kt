package com.sadikahmetozdemir.sadik_fodamy.ui.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentRecipeDetailBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeDetailFragment :
    BaseFragment<FragmentRecipeDetailBinding, RecipeDetailViewModel>(R.layout.fragment_recipe_detail) {

    private var args: RecipeDetailFragmentArgs? = null
    private fun getRecipeDetail(recipeID: Int) {

        viewModel.getRecipeDetail(recipeID)
    }

    private fun getRecipeDetailComment(recipeID: Int) {

        viewModel.getRecipeDetailComment(recipeID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { itArguments ->
            args = RecipeDetailFragmentArgs.fromBundle(itArguments)
        }
        args?.recipeId?.let { getRecipeDetail(it) }
        args?.recipeId?.let { getRecipeDetailComment(it) }

        initObservers()
    }

    private fun initObservers() {
        viewModel.recipeDetail.observe(viewLifecycleOwner) { recipeDetail ->
            recipeDetail?.let { renderRecipeDetail(it) }
        }
        viewModel.showErrorMessage.observe(viewLifecycleOwner) {
            requireActivity().runOnUiThread {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun renderRecipeDetail(recipeDetail: EditorChoiceModel) {
        setFragmentResultListener("request_unfollow") { requestKey, bundle ->
                if (bundle.getBoolean("unfollow", false)) {
                    recipeDetail.user?.id.let {
                        it?.let { it1 -> viewModel?.userUnfollow(it1) }
                    }
                }
            }
        }
    }

