package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentSignUpBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterResponseModel
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    val viewModel by viewModels<SignUpViewModel>()
    var binding: FragmentSignUpBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()


        binding?.textSignin?.setOnClickListener {
            goLogin()
        }
        binding?.buttonSignUp?.setOnClickListener {
            if (viewModel.validateFile(
                    binding?.editTextTextPersonName?.text.toString(),
                    binding?.editTextTextEmailAddress?.text.toString(),
                    binding?.editTextPassword?.text.toString()
                )
            ) lifecycleScope.launch{
                viewModel.sendRegisterRequest(
                    binding?.editTextTextPersonName?.text.toString(),
                    binding?.editTextTextEmailAddress?.text.toString(),
                    binding?.editTextPassword?.text.toString(), "sadik", "ahmet"
                )
            }
        }

    }

    fun initObservers() {
        viewModel.showUsernameError.observe(viewLifecycleOwner) { usernameError ->
            binding?.textInputLayoutUsername?.error = usernameError
        }
        viewModel.showEmailError.observe(viewLifecycleOwner) { emailError ->
            binding?.textInputLayoutEmail?.error = emailError
        }
        viewModel.showPasswordError.observe(viewLifecycleOwner) { passwordError ->
            binding?.textInputPassword?.error = passwordError
        }
        viewModel.showErrorMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it, Toast.LENGTH_SHORT).show()
        }
    }


    fun goLogin() {
        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)


    }



}