package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentForgotPasswordBinding
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ForgotPassword : Fragment() {
    var binding: FragmentForgotPasswordBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.imageBack?.setOnClickListener {
            goBackToLogin()

        }


    }

    fun goBackToLogin() {
        findNavController().navigate(R.id.action_forgotPassword_to_loginFragment)

    }



    }


