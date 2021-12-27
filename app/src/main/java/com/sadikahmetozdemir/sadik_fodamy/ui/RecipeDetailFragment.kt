package com.sadikahmetozdemir.sadik_fodamy.ui

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentRecipeDetailBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.CommentResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RecipeDetailCommentResponseModel
import com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout.HomeTablayoutFragmentDirections
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.load
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.loadCircleCrop
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.spannableNum
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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

        binding = FragmentRecipeDetailBinding.inflate(layoutInflater)
        return binding?.root
    }

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
        viewModel.recipeDetailComment.observe(viewLifecycleOwner) { recipeDetailComment ->

            recipeDetailComment?.let { renderRecipeDetailComment(recipeDetailComment) }

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

            }


        }


    }

    fun renderRecipeDetail(recipeDetail: EditorChoiceModel) {

        binding?.apply {
            tvFoodTitle.text = recipeDetail.title
            tvFoodDescription.text = recipeDetail.category?.name
            tvTime.text = recipeDetail.difference.toString()
            var commentText = binding?.root?.context?.getString(R.string.comment)
                ?.let { String.format(it, recipeDetail.comment_count) }
            tvComment.text =
                commentText?.spannableNum(0, recipeDetail.comment_count.toString().length)
            tvLike.text =
                binding?.root?.context?.getString(R.string.like)
                    ?.let {
                        String.format(it, recipeDetail.like_count)
                            .spannableNum(0, recipeDetail.like_count.toString().length)
                    }
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
            layoutIngredients.tvIngredients.text = recipeDetail.ingredients
            layoutIngredients.tvNumber.text = recipeDetail.number_of_person?.text.toString()
            layoutDirections.tvTittle.text = "Yapılışı"
            layoutDirections.tvNumber.text = "${recipeDetail.time_of_recipe?.text.toString()} dk"
            layoutDirections.tvIngredients.text = recipeDetail.directions
            ivFood.load(url = recipeDetail.images?.get(0)?.url)
            ivUser.loadCircleCrop(url = recipeDetail?.user?.image?.toString())
            ivEditorChoiceMedal.isVisible = (recipeDetail.isEditorChoice == true)
            layoutDirections.ivCard.setImageResource(R.drawable.ic_clock_icon)
            toolbar.logoFodamy.visibility = View.GONE
            val turkishLocale = Locale.forLanguageTag("tr")
            toolbar.tvFoodDetailTitle.text = recipeDetail.category?.name?.uppercase(turkishLocale)
            toolbar.ivLogout.visibility = View.GONE

            toolbar.ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbar.tvBack.setOnClickListener {
                findNavController().popBackStack()
            }
            ivFood.setOnClickListener {
                openRecipeImages(recipeDetail)
            }
            if (recipeDetail.is_liked == true) {
                ivLike.imageTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.primary
                        )
                    )

            }
            else{  ivLike.imageTintList =
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
                        viewModel.recipeLike(it1)
                    }
                } else {
                    recipeDetail.id?.let {
                        viewModel.recipeDislike(it)
                    }

                }

            }


        }


    }

    fun renderRecipeDetailComment(commentResponseModel: CommentResponseModel) {

        if (commentResponseModel.data.isNotEmpty()) {
            binding?.apply {
                tvTittleComment.text = "Yorumlar"
                ivUserComment.loadCircleCrop(url = commentResponseModel.data.get(0).user?.image?.url.toString())
                tvUsernameComment.text = commentResponseModel.data.get(0).user?.username
                tvRecipeComment.text =
                    commentResponseModel.data.get(0).user?.recipe_count.toString()
                tvFollowerComment.text =
                    commentResponseModel.data.get(0).user?.followed_count.toString()
                tvTimeComment.text = commentResponseModel.data.get(0).difference.toString()
                tvUserComment.text = commentResponseModel.data.get(0).text


            }


        }
    }

    fun openRecipeImages(recipeID: EditorChoiceModel) {

        findNavController().navigate(RecipeDetailFragmentDirections.toRecipeImages(recipeID))

    }


}