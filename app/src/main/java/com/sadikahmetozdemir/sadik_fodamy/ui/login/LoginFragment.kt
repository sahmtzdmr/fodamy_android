package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentLoginBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {
    var bindig: FragmentLoginBinding?=null
    private val BASE_URL="https://fodamy.mobillium.com/"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindig= FragmentLoginBinding.inflate(inflater,container,false)
        return bindig?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         bindig?.textRegister?.setOnClickListener{
             goRegister()

             val retrofit= Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
             val service=retrofit.create(LoginAPI::class.java)


         }


    }

    fun goRegister(){

        findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }
}
