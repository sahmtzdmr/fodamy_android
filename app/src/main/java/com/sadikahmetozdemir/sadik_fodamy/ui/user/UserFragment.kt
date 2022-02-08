package com.sadikahmetozdemir.sadik_fodamy.ui.user

import android.os.Bundle
import android.view.View
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>(R.layout.fragment_user) {
    @Inject
    lateinit var userProfileAdapter: UserProfileAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvLikedRecipes.apply {
            setHasFixedSize(true)
            adapter = userProfileAdapter
        }
        getUserLikedRecipes()

    }

    private fun getUserLikedRecipes() {
        viewModel.recipes.observe(viewLifecycleOwner) {
            userProfileAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}