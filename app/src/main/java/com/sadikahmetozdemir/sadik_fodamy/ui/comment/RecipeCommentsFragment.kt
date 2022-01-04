package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentRecipeCommentsBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.hideSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeCommentsFragment :
    BaseFragment<FragmentRecipeCommentsBinding, RecipeCommentsViewModel>(R.layout.fragment_recipe_comments) {
    @Inject
    lateinit var recipeCommentsAdapter: RecipeCommentsAdapter
    var recipeComment: EditorChoiceModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.getRecipeCommentsItem()
        recipeCommentsAdapter.itemClicked={
            viewModel.navigate(RecipeCommentsFragmentDirections.tocommentDialogFragment())
//            viewModel.deleteRecipeComments(it)
        }
        renderRecipeComment()

        getRecipeComments()
    }

    fun getRecipeComments() {
        viewModel?.recipes?.observe(viewLifecycleOwner) {
            recipeCommentsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        viewModel?.event?.observe(viewLifecycleOwner) {
            when (it) {
                is RecipeCommentsEvent.Success -> {
                    requireView().clearFocus()
                    binding?.root?.let { it1 -> context?.hideSoftKeyboard(it1) }
                    recipeCommentsAdapter.refresh()
                    binding?.etComment?.setText("")
                }
            }

        }
    }

    fun renderRecipeComment() {
        binding?.apply {
            toolbar.ivLogout.visibility = View.GONE
            toolbar.ivShare.visibility = View.GONE
            toolbar.logoFodamy.visibility = View.GONE
            toolbar.tvFoodDetailTitle.setText(R.string.comments)
            etComment.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0?.length!! > 0) {
                        ivSend.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.primary
                            )
                        )
                    } else {
                        ivSend.isEnabled = false
                        ivSend.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.color_gray
                            )
                        )
                    }

                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
            ivSend.setOnClickListener {
                viewModel?.postRecipeComment(binding?.etComment?.text.toString())
            }

            setFragmentResultListener("request_delete") { requestKey, bundle ->
                if (bundle.getBoolean("delete", false)) {

                    recipeComment?.id?.let {
                        viewModel.deleteRecipeComments(commentId = it)
                    }
                }
            }

        }
        binding?.recyclerViewComments?.apply {
            setHasFixedSize(true)
            adapter = recipeCommentsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }
}
