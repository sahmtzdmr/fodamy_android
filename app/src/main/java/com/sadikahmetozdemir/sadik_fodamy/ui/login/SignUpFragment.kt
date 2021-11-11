package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentSignUpBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SignUpFragment : Fragment() {
    var binding:FragmentSignUpBinding?=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
         binding=FragmentSignUpBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.textSignin?.setOnClickListener{
            goLogin()



        }



    }


    fun goLogin(){
        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)



    }
}