package com.sadikahmetozdemir.sadik_fodamy.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {
    @Inject
    lateinit var dataHelperManager: DataHelperManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            delay(1000)

            if (dataHelperManager.isFirstAttach()) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToIntroFragment())
                dataHelperManager.firstAttach()
            } else {
                findNavController().navigate(SplashFragmentDirections.toHomeFragment())
            }
        }
    }
}
