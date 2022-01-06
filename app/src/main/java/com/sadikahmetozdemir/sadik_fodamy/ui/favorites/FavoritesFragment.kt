package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(R.layout.fragment_favorites) {
    @Inject
    lateinit var favoritesItemAdapter: FavoritesItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavoriteItemsCategory()
        initObserve()
        binding?.toolbar?.ivBack?.visibility = View.GONE
        binding?.toolbar?.tvFoodDetailTitle?.visibility = View.GONE
        binding?.toolbar?.ivShare?.visibility = View.GONE
        binding?.toolbar?.tvBack?.visibility = View.GONE
        binding?.toolbar?.ivLogout?.setOnClickListener {
            viewModel?.logoutRequest()
        }

        binding?.recyclerViewMain?.apply {

            setHasFixedSize(true)
            adapter = favoritesItemAdapter
        }
        favoritesItemAdapter.itemClicked = {
            viewModel?.toCategories(it)
        }
        favoritesItemAdapter.childItemClicked = {
            findNavController().navigate(FavoritesFragmentDirections.toRecipeDetail(it))
        }
    }

    fun getFavoriteItemsCategory() {
        viewModel?.recipes?.observe(viewLifecycleOwner) {
            favoritesItemAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    fun initObserve() {
        viewModel?.event?.observe(viewLifecycleOwner) {
            println(it)
        }
    }
}
