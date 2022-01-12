package com.sadikahmetozdemir.sadik_fodamy.ui.intro

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentIntroBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.local.IntroModel
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage

class IntroFragment : BaseFragment<FragmentIntroBinding, IntroViewModel>(R.layout.fragment_intro) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager2.adapter = IntroAdapter(prepareIntroList())
        binding.viewPager2.let { binding.indicator.setViewPager2(it) }
        binding.viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == prepareIntroList().size - 1) { // TODO:will refactor
                    binding.btNext.text = getString(R.string.button_start)
                } else {
                    binding.btNext.text = getString(R.string.next_page)
                }
            }
        })
        binding.btNext.setOnClickListener {
            if (binding.viewPager2.currentItem != prepareIntroList().size - 1
            ) {
                binding.viewPager2.setCurrentItem(
                    (binding.viewPager2.currentItem) + 1,
                    true
                )
                SharedPreferanceStorage.isTutorialCompleted = true
            } else {
                findNavController().navigate(IntroFragmentDirections.toHomeFragment())
            }
        }
    }

    private fun prepareIntroList(): ArrayList<IntroModel> {

        return arrayListOf(
            IntroModel(
                drawableId = R.drawable.walkthrough_image_first,
                tittle = getString(R.string.welcome),
                description = getString(R.string.firstInfo)
            ),
            IntroModel(
                drawableId = R.drawable.walkthrough_image_2,
                tittle = getString(R.string.title2),
                description = getString(R.string.firstInfo)
            ),
            IntroModel(
                drawableId = R.drawable.walkthrough_image_3,
                tittle = getString(R.string.title3),
                description = getString(R.string.firstInfo)
            ),
            IntroModel(
                drawableId = R.drawable.walkthrough_image_4,
                tittle = getString(R.string.title4),
                description = getString(R.string.firstInfo)

            )
        )
    }
}
