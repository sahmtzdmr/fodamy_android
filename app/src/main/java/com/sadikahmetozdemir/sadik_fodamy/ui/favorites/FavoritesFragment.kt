package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    val viewmodel by viewModels<FavoritesViewModel>()

    @Inject
    lateinit var favoritesItemAdapter: FavoritesItemAdapter
    var binding: FragmentFavoritesBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFavoritesBinding.inflate(layoutInflater)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavoriteItemsCategory()

        binding?.recyclerViewMain?.apply {

            setHasFixedSize(true)
            adapter =favoritesItemAdapter




        }
    }
    fun getFavoriteItemsCategory(){
        viewmodel.recipes.observe(viewLifecycleOwner){
            favoritesItemAdapter.submitData(viewLifecycleOwner.lifecycle,it)
        }
    }
}