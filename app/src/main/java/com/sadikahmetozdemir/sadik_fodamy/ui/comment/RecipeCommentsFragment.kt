package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentRecipeCommentsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class RecipeCommentsFragment :
    BaseFragment<FragmentRecipeCommentsBinding, RecipeCommentsViewModel>(R.layout.fragment_recipe_comments) {
    @Inject
    lateinit var recipeCommentsAdapter: RecipeCommentsAdapter
    private val args: RecipeCommentsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeCommentsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recipeID = args.recipeID

        if (recipeID != null) {
            recipeID.let { viewModel?.getRecipeCommentsItem(it) }
        }
        binding?.apply {
            toolbar.ivLogout.visibility = View.GONE
            toolbar.ivShare.visibility = View.GONE
        }
        binding?.recyclerViewComments?.apply {
            setHasFixedSize(true)
            adapter = recipeCommentsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        getRecipeComments()
    }

    fun getRecipeComments() {
        viewModel?.recipes?.observe(viewLifecycleOwner) {
            recipeCommentsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}
