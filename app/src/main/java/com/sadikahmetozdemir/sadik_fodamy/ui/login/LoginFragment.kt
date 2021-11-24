package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentLoginBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LoginRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LoginResponseModel
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    val viewModel by viewModels<LoginViewModel>()
    var binding: FragmentLoginBinding? = null

    private val arrayList: ArrayList<UserModel>? = null
    @Inject  lateinit var prefs:SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)



        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()


        binding?.textRegister?.setOnClickListener {
            goRegister()
        }
        binding?.textForgotPassword?.setOnClickListener {
            goForgotPassword()
        }
        binding?.buttonLogin?.setOnClickListener {


            if (viewModel.validateFields(binding?.editTextTextEmailAddress?.text.toString(),binding?.editTextPassword?.text.toString())) {
               lifecycleScope.launch {
                   viewModel.sendLoginRequest(
                       binding?.editTextTextEmailAddress?.text.toString(),
                       binding?.editTextPassword?.text.toString()
                   )
               }
            }


        }


    }

    fun initObservers(){
        viewModel.showEmailError.observe(viewLifecycleOwner){ emailError->
            binding?.textInputLayoutEmail?.error=emailError
        }
        viewModel.showPasswordError.observe(viewLifecycleOwner){ passwordError->
            binding?.textInputLayoutPassword?.error=passwordError
        }

        viewModel.showErrorMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        }

    }
    fun goForgotPassword() {
        findNavController().navigate(R.id.action_loginFragment_to_forgotPassword)
    }

    fun goRegister() {

        findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }


}
