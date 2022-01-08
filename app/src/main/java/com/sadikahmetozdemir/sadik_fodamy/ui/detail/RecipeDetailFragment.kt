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
        viewModel.event.observe(viewLifecycleOwner) { event ->

            when (event) {
                is RecipeDetailEvent.IsLiked -> {
                    binding?.ivLike?.imageTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.primary
                            )
                        )
                }
                is RecipeDetailEvent.OpenDialog -> findNavController().navigate(event.direction)

                is RecipeDetailEvent.IsFollowed -> {
                    binding?.btFollow?.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.primary
                            )
                        )
                    binding?.btFollow?.setText(R.string.following)
                    binding?.btFollow?.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.cardview_light_background
                        )
                    )
                }

                is RecipeDetailEvent.IsUnfollowed -> {
                    binding?.btFollow?.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.cardview_light_background
                            )
                        )
                    binding?.btFollow?.setText(R.string.follow)
                    binding?.btFollow?.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.primary
                        )
                    )
                }
            }
        }
    }

    fun renderRecipeDetail(recipeDetail: EditorChoiceModel) {

        binding?.apply {
            recipeDetail.user?.is_following?.let { setUpFollowButton(it) }
            btFollow.setOnClickListener {
                if (recipeDetail.user?.is_following == false) {
                    recipeDetail.user!!.id.let {
                        it?.let { it1 -> viewModel?.userFollow(it1) }
                    }
                } else {
                    viewModel?.bottomSheetUnfollow()
                }
            }


          ivFood.setOnClickListener {
                viewModel?.openRecipeImages(recipeDetail)
            }
            if (recipeDetail.is_liked == true) {
                ivLike.imageTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.primary
                        )
                    )
            } else {
                ivLike.imageTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.cinder
                        )
                    )
            }
            ivLike.setOnClickListener {
                if (recipeDetail.is_liked == false) {

                    recipeDetail.id?.let { it1 ->
                        viewModel?.recipeLike(it1)
                    }
                } else {
                    recipeDetail.id?.let {
                        viewModel?.recipeDislike(it)
                    }
                }
            }
            setFragmentResultListener("request_unfollow") { requestKey, bundle ->
                if (bundle.getBoolean("unfollow", false)) {
                    recipeDetail.user?.id.let {
                        it?.let { it1 -> viewModel?.userUnfollow(it1) }
                    }
                }
            }
        }
    }

    fun setUpFollowButton(isFollowed: Boolean) {

        if (isFollowed) {
            binding?.btFollow?.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.primary))
            binding?.btFollow?.setText(R.string.following)
            binding?.btFollow?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.cardview_light_background
                )
            )
        } else {
            binding?.btFollow?.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cardview_light_background
                    )
                )
            binding?.btFollow?.setText(R.string.follow)
            binding?.btFollow?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.primary
                )
            )
        }
    }
}
