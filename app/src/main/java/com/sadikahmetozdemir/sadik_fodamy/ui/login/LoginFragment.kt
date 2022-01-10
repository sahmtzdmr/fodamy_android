package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    fun initObservers() {
        viewModel?.user?.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Başarıyla giriş yapıldı", Toast.LENGTH_SHORT).show()
        }
    }
}
