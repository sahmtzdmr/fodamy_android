package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import android.os.Bundle
import android.view.View
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(R.layout.fragment_favorites) {
    @Inject
    lateinit var favoritesItemAdapter: FavoritesItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavoriteItemsCategory()
        binding.toolbar.ivLogout.setOnClickListener {
            viewModel.logoutRequest()
        }

        binding.recyclerViewMain.apply {
            setHasFixedSize(true)
            adapter = favoritesItemAdapter
        }
        favoritesItemAdapter.itemClicked = {
            viewModel.toCategories(it)
        }
        favoritesItemAdapter.childItemClicked = {
            viewModel.openDetailScreen(it)
        }
    }

    private fun getFavoriteItemsCategory() {
        viewModel.recipes.observe(viewLifecycleOwner) {
            favoritesItemAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}
