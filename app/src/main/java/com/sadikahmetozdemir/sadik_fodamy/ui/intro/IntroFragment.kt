package com.sadikahmetozdemir.sadik_fodamy.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
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
        dataBinding?.viewPager2?.adapter = IntroAdapter(prepareIntroList())
        dataBinding?.viewPager2?.let { dataBinding?.indicator?.setViewPager2(it) }
        dataBinding?.viewPager2?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == prepareIntroList().size - 1) {//TODO:will refactor
                    dataBinding?.btNext?.text = getString(R.string.button_start)
                } else {
                    dataBinding?.btNext?.text = getString(R.string.next_page)
                }
            }
        })
        dataBinding?.btNext?.setOnClickListener {
            if (dataBinding?.viewPager2?.currentItem != prepareIntroList().size - 1
            )
                dataBinding?.viewPager2?.setCurrentItem(
                    (dataBinding?.viewPager2?.currentItem ?: 0) + 1,
                    true
                )
        }

    }

    private fun prepareIntroList(): ArrayList<IntroModel> {

        return arrayListOf(
            IntroModel(
                drawableId = R.drawable.walkthrough_image_first,
                tittle = getString(R.string.welcome),
                description = getString(R.string.firstInfo)
            ), IntroModel(
                drawableId = R.drawable.walkthrough_image_2,
                tittle = getString(R.string.title2),
                description = getString(R.string.firstInfo)
            ), IntroModel(
                drawableId = R.drawable.walkthrough_image_3,
                tittle = getString(R.string.title3),
                description = getString(R.string.firstInfo)
            ), IntroModel(
                drawableId = R.drawable.walkthrough_image_4,
                tittle =getString(R.string.title4),
                description =getString(R.string.firstInfo)

            )
        )
    }
}