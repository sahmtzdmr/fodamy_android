package com.sadikahmetozdemir.sadik_fodamy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentRecipeImagePopUpBinding

class RecipeImagePopUpFragment : Fragment() {
    var binding: FragmentRecipeImagePopUpBinding? = null
    private var args: RecipeImagePopUpFragmentArgs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeImagePopUpBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            args = RecipeImagePopUpFragmentArgs.fromBundle(it)
        }
        args?.editorChoiseModel?.let {


            val urlList = arrayListOf<String>()

                it.images?.get(0)?.url?.let { it1 ->
                    urlList.add(it1)
                    urlList.add(it1)
                }

            binding?.imageViewpager2?.adapter = RecipeImageAdapter(urlList)
            binding?.imageViewpager2.let { it?.let { it1 -> binding?.indicator?.setViewPager2(it1) } }
            binding?.ivClose?.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}
