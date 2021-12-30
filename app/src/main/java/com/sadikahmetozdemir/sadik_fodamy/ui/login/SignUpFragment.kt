package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel >(R.layout.fragment_sign_up) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding?.apply {
            vm = viewModel
            textSignin.setOnClickListener {
                viewModel?.goLogin()
                requireView().clearFocus()
            }
        }
    }

    fun initObservers() {
        viewModel?.showUsernameError?.observe(viewLifecycleOwner) { usernameError ->
            binding?.textInputLayoutUsername?.error = usernameError
        }
        viewModel?.showEmailError?.observe(viewLifecycleOwner) { emailError ->
            binding?.textInputLayoutEmail?.error = emailError
        }
        viewModel?.showPasswordError?.observe(viewLifecycleOwner) { passwordError ->
            binding?.textInputPassword?.error = passwordError
        }
        viewModel?.showErrorMessage?.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel?.user?.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Başarıyla kayıt gerçekleşti", Toast.LENGTH_SHORT).show()
        }
    }
}
