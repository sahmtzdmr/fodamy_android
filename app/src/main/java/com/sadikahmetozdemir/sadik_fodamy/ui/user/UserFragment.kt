package com.sadikahmetozdemir.sadik_fodamy.ui.user

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>(R.layout.fragment_user) {
    @Inject
    lateinit var userProfileLikesAdapter: UserProfileLikesAdapter
    @Inject
    lateinit var userProfileRecipesAdapter: UserProfileRecipesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvLikedRecipes.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = userProfileLikesAdapter
        }
        binding.rvRecipes.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = userProfileRecipesAdapter
        }
        getUserDetails()
    }

    private fun getUserDetails() {
        viewModel.likes.observe(viewLifecycleOwner) {
            userProfileLikesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        viewModel.recipes.observe(viewLifecycleOwner) {
            userProfileRecipesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}
