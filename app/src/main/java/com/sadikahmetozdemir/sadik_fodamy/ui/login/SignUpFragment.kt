package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentSignUpBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterResponseModel
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SignUpFragment : Fragment() {
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


        binding?.textSignin?.setOnClickListener {
            goLogin()
        }
        binding?.buttonSignUp?.setOnClickListener {
            if (validateFile()) {
                sendRegisterRequest(
                    binding?.editTextTextPersonName?.text.toString(),
                    binding?.editTextTextEmailAddress?.text.toString(),
                    binding?.editTextPassword?.text.toString(), "sadik", "ahmet"
                )
            }
        }

    }


    fun goLogin() {
        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)


    }

    private fun validateFile(): Boolean {
        val username = binding?.editTextTextPersonName?.text.toString().trim()
        val email = binding?.editTextTextEmailAddress?.text.toString().trim()
        val password = binding?.editTextPassword?.text.toString().trim()

        if (username.isEmpty()) {
            binding?.textInputLayoutUsername?.error = "Kullanıcı adı kısmı boş bırakılamaz."
            return false

        }


        if (email.isEmpty()) {
            binding?.textInputLayoutEmail?.error="Email kısmı boş bırakılamaz."
            return false
        }
        if (password.isEmpty()) {
            binding?.textInputPassword?.error="Şifre kısmı boş bırakılamaz."
            return false
        }


        return true


    }

    fun sendRegisterRequest(
        username: String?,
        email: String?,
        password: String?,
        name: String?,
        surname: String?
    ) {

        val retrofit = Retrofit.Builder()
            .baseUrl(SharedPreferanceStorage.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LoginAPI::class.java)
        val call = service.registerRequest(
            RegisterRequestModel("asdfgh@mobillium.com", "123456", "sadikah", "sadik", "ozdemir")
        )
        call.enqueue(object : Callback<RegisterResponseModel> {
            override fun onResponse(
                call: Call<RegisterResponseModel>,
                response: Response<RegisterResponseModel>
            ) {
                if (response.isSuccessful) {
                    response.body().let {
                        Log.d("sad", "onResponse: ")
                    }


                } else {
                    response.errorBody().let {
                        Log.d("sad", "onResponse: ")
                    }
                }

            }

            override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
                t.printStackTrace()

            }
        })


    }
}