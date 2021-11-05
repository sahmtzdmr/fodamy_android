package com.sadikahmetozdemir.sadik_fodamy.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentIntroBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.local.IntroModel

class IntroFragment : Fragment() {
    private var dataBinding: FragmentIntroBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentIntroBinding.inflate(layoutInflater)
        return dataBinding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding?.viewPager2?.adapter=IntroAdapter(prepareIntroList())
    }

    private fun prepareIntroList(): ArrayList<IntroModel> {

        return arrayListOf(
            IntroModel(
                drawableId = R.drawable.walkthrough_image_first,
                tittle = "first",
                description = "descfirst"
            ),IntroModel(
                drawableId = R.drawable.walkthrough_image_2,
                tittle = "first",
                description = "desc2"
            ),IntroModel(
                drawableId = R.drawable.walkthrough_image_3,
                tittle = "first",
                description = "desc3"
            ))
    }
}