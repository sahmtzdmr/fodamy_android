package com.sadikahmetozdemir.sadik_fodamy.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentRecipeDetailBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {
    val viewModel by viewModels<RecipeDetailViewModel>()
    private var args: RecipeDetailFragmentArgs? = null
    var binding: FragmentRecipeDetailBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentRecipeDetailBinding.inflate(layoutInflater)
        return binding?.root
    }

    private fun getRecipeDetail(recipeID: Int) {

        viewModel.getRecipeDetail(recipeID)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { itArguments ->
            args = RecipeDetailFragmentArgs.fromBundle(itArguments)

        }
        args?.recipeId?.let { getRecipeDetail(it) }





        initObservers()
    }

    private fun initObservers() {

        viewModel.recipeDetail.observe(viewLifecycleOwner) { recipeDetail ->

           if (recipeDetail != null) {
               renderRecipeDetail(recipeDetail)
           }



        }


    }

    fun renderRecipeDetail(recipeDetail: EditorChoiceModel) {

        binding?.apply {
            tvFoodTitle.text = recipeDetail.title
            tvFoodDescription.text = recipeDetail.category?.name
            tvTime.text=recipeDetail.difference.toString()
            tvComment.text =
                binding?.root?.context?.getString(R.string.comment)
                    ?.let { String.format(it, recipeDetail.comment_count) }
            tvLike.text =
                binding?.root?.context?.getString(R.string.like)
                    ?.let { String.format(it, recipeDetail.like_count) }
            tvUsername.text = recipeDetail.user?.username
            tvRecipe.text = binding?.root?.context?.getString(R.string.recipe)?.let {
                String.format(
                    it,
                    recipeDetail.user?.recipe_count
                )
            }
            tvFollower.text = binding?.root?.context?.getString(R.string.follower)?.let {
                String.format(
                    it,
                    recipeDetail.user?.following_count
                )
            }
            layoutIngredients.tvIngredients.text=recipeDetail.ingredients
            layoutIngredients.tvNumber.text= recipeDetail.number_of_person?.text.toString()
            layoutDirections.tvTittle.text="Yapılışı"
            layoutDirections.tvNumber.text=recipeDetail.time_of_recipe?.text.toString()
            layoutDirections.tvIngredients.text=recipeDetail.directions








        }

    }


}