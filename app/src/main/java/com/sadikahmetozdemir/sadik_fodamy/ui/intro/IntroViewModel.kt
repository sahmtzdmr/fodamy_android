package com.sadikahmetozdemir.sadik_fodamy.ui.intro

import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel

class IntroViewModel : BaseViewModel() {

    fun onClose() {
        navigate(IntroFragmentDirections.toHomeFragment())
    }
}
