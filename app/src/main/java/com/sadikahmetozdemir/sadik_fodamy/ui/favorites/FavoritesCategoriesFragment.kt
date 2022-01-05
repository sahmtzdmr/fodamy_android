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
        var categoryID = args?.categoryID

        if (categoryID != null) {
            viewModel?.getFavoriteCategoriesItem(categoryID)
        }

        binding?.apply {
            val turkishLocale = Locale.forLanguageTag("tr")
            toolbar.ivShare.visibility = View.GONE
            toolbar.logoFodamy.visibility = View.GONE
            toolbar.tvFoodDetailTitle.text = args.title.uppercase(turkishLocale)
            toolbar.tvBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbar.ivLogout.setOnClickListener {
                viewModel?.logoutRequest()
                initObserve()
            }
            toolbar.ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        favoritesCategoriesAdapter.itemClickedToImages = {
            viewModel?.toRecipeDetail(it)
        }

        binding?.rvFavoriteDetails?.apply {
            setHasFixedSize(true)
            adapter = favoritesCategoriesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        getFavoriteCategories()
    }

    fun getFavoriteCategories() {
        viewModel?.recipes?.observe(viewLifecycleOwner) {
            favoritesCategoriesAdapter.submitData(
                viewLifecycleOwner.lifecycle, it
            )
        }
    }

    fun initObserve() {
        viewModel?.event?.observe(viewLifecycleOwner) { event ->
            when (event) {
                is FavoritesEvent.ShowMessage -> println(event.message)
            }
        }
    }
}
