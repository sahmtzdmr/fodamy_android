package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentFavoritesCategoriesBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesCategoriesFragment : Fragment() {
    val viewModel by viewModels<FavoritesCategoriesViewModel>()

    @Inject
    lateinit var favoritesCategoriesAdapter: FavoritesCategoriesAdapter
    private var binding: FragmentFavoritesCategoriesBinding? = null
    private val args:FavoritesCategoriesFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentFavoritesCategoriesBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var item : EditorChoiceModel?=null
        var categoryID=args?.categoryID

            if(categoryID!=null){
                viewModel.getFavoriteCategoriesItem(categoryID)
            }


    binding?.apply {
        val turkishLocale = Locale.forLanguageTag("tr")
        toolbar.ivShare.visibility=View.GONE
        toolbar.logoFodamy.visibility=View.GONE
        toolbar.tvFoodDetailTitle.text=args.title.uppercase(turkishLocale)


    }

    binding?.rvFavoriteDetails?.apply {
        setHasFixedSize(true)
        adapter=favoritesCategoriesAdapter
        layoutManager=
            LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

    }
        getFavoriteCategories()

    }

    fun getFavoriteCategories(){
        viewModel.recipes.observe(viewLifecycleOwner){
            favoritesCategoriesAdapter.submitData(
                viewLifecycleOwner.lifecycle,it
            )
        }
    }

}