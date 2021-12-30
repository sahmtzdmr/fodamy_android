package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    @Inject
    lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding?.apply {
            vm = viewModel
            textForgotPassword.setOnClickListener { viewModel?.goForgotPassword() }
            textRegister.setOnClickListener { viewModel?.goRegister() }
        }
    }

    fun initObservers() {
        viewModel?.user?.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Başarıyla giriş yapıldı", Toast.LENGTH_SHORT).show()
        }
    }
}
