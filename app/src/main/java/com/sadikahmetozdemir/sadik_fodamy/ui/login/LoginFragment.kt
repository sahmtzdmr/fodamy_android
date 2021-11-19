package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentLoginBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LoginRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LoginResponseModel
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {
    var binding: FragmentLoginBinding? = null

    private val arrayList: ArrayList<UserModel>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.textRegister?.setOnClickListener {
            goRegister()
        }
        binding?.textForgotPassword?.setOnClickListener {
            goForgotPassword()
        }
        binding?.buttonLogin?.setOnClickListener {


            if (validateFields()) {
                sendLoginRequest(
                    binding?.editTextTextEmailAddress?.text.toString(),
                    binding?.editTextPassword?.text.toString()
                )
            }


        }


    }

    private fun validateFields(): Boolean {
        val email = binding?.editTextTextEmailAddress?.text.toString().trim()
        val password = binding?.editTextPassword?.text.toString().trim()
        if (email.isEmpty() && email.contains("@")) {
            binding?.textInputLayoutEmail?.error = "Email alanı boş kalamaz."

            return false
        }
        if (password.isEmpty()) {
            binding?.textInputLayoutPassword?.error = "Şifre alanı boş kalamaz."
            return false
        }

        return true
    }

    fun goForgotPassword() {
        findNavController().navigate(R.id.action_loginFragment_to_forgotPassword)
    }

    fun goRegister() {

        findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    fun sendLoginRequest(username: String, password: String) {
        val sharedPreferences = context?.getSharedPreferences(
            "com.sadikahmetozdemir.sadik_fodamy",
            Context.MODE_PRIVATE
        )

        val retrofit = Retrofit.Builder()
            .baseUrl(SharedPreferanceStorage.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(LoginAPI::class.java)
        val call = service.loginRequest(LoginRequestModel(username, password))
        call.enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                if (response.isSuccessful) {

                    response.body()?.let {
                        var userID = it.user?.id?.let { it1 ->
                            sharedPreferences?.edit()?.putInt(
                                SharedPreferanceStorage.PREFS_USER_ID,
                                it1
                            )
                        }
                        println(it.user?.id)

                        var userToken = sharedPreferences?.edit()
                            ?.putString(SharedPreferanceStorage.PREFS_USER_TOKEN, it.token)
                        println(it.token)
                    }


                } else {
                    response.errorBody()?.let {
                        Log.d("sda", "onResponse:")

                    }

                }

            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                t.printStackTrace()
            }


        })

    }
}
