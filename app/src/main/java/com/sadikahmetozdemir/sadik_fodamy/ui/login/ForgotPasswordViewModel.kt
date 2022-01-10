package com.sadikahmetozdemir.sadik_fodamy.ui.login

import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel

class ForgotPasswordViewModel : BaseViewModel() {
    private fun goBackToLogin() {
        popBackStack()
    }
}