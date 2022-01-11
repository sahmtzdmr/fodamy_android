package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentFavoritesCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesCategoriesFragment : BaseFragment<FragmentFavoritesCategoriesBinding, FavoritesCategoriesViewModel>(
    R.layout.fragment_favorites_categories
) {
    @Inject
    lateinit var favoritesCategoriesAdapter: FavoritesCategoriesAdapter
    private val args: FavoritesCategoriesFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryID = args.categoryID
        viewModel.getFavoriteCategoriesItem(categoryID)


        binding.apply {
            toolbar.ivLogout.setOnClickListener {
                viewModel.logoutRequest()
            }
        }
        favoritesCategoriesAdapter.itemClickedToImages = {
            viewModel.toRecipeDetail(it)
        }

        binding.rvFavoriteDetails.apply {
            setHasFixedSize(true)
            adapter = favoritesCategoriesAdapter
        }
        getFavoriteCategories()
    }
    fun getFavoriteCategories() {
        viewModel.recipes.observe(viewLifecycleOwner) {
            favoritesCategoriesAdapter.submitData(
                viewLifecycleOwner.lifecycle, it
            )
        }
    }
}
