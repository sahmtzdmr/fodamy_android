package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    val viewModel by viewModels<FavoritesViewModel>()

    @Inject
    lateinit var favoritesItemAdapter: FavoritesItemAdapter
    var binding: FragmentFavoritesBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavoriteItemsCategory()
        initObserve()
        binding?.toolbar?.ivBack?.visibility = View.GONE
        binding?.toolbar?.tvFoodDetailTitle?.visibility = View.GONE
        binding?.toolbar?.ivShare?.visibility = View.GONE
        binding?.toolbar?.tvBack?.visibility = View.GONE
        binding?.toolbar?.ivLogout?.setOnClickListener {
            viewModel.logoutRequest()

        }

        binding?.recyclerViewMain?.apply {

            setHasFixedSize(true)
            adapter = favoritesItemAdapter


        }
        favoritesItemAdapter.itemClicked = {
            findNavController().navigate(
                FavoritesFragmentDirections.actionFavoritesFragmentToFavoritesCategoriesFragment(
                    it.id,
                    it.name
                )
            )

        }
        favoritesItemAdapter.childItemClicked = {
            findNavController().navigate(FavoritesFragmentDirections.toRecipeDetail(it))
        }

    }

    fun getFavoriteItemsCategory() {
        viewModel.recipes.observe(viewLifecycleOwner) {
            favoritesItemAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    fun initObserve() {
        viewModel.event.observe(viewLifecycleOwner) {
            println(it)


            }


        }

    }
