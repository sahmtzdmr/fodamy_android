package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Display
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {
    var bindig: FragmentLoginBinding? = null
    private val BASE_URL = "https://fodamy.mobillium.com/"
    private val arrayList: ArrayList<UserModel>? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindig = FragmentLoginBinding.inflate(inflater, container, false)
        return bindig?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bindig?.textRegister?.setOnClickListener {
            goRegister()
        }
        bindig?.buttonLogin?.setOnClickListener {



            if (validateFile()) {
                sendLoginRequest(
                    bindig?.editTextTextEmailAddress?.text.toString(),
                    bindig?.editTextPassword?.text.toString()
                )
            }


        }


    }

    private fun validateFile(): Boolean {
        val email = bindig?.editTextTextEmailAddress?.text.toString().trim()
        val password = bindig?.editTextPassword?.text.toString().trim()
        if (email.isEmpty()) {
            bindig?.textInputLayoutEmail?.error = "Email alanı boş kalamaz."
            return false
        }
        if (password.isEmpty()) {
            bindig?.textInputLayoutPassword?.error = "Şifre alanı boş kalamaz."
            return false
        }

        return true
    }

    fun goRegister() {

        findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    fun sendLoginRequest(email: String, password: String) {
        val sharedPreferences=context?.getSharedPreferences("com.sadikahmetozdemir.sadik_fodamy",Context.MODE_PRIVATE)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(LoginAPI::class.java)
        val call = service.loginRequest(LoginRequestModel("sadikaga", "fodamy45+"))
        call.enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                if (response.isSuccessful) {

                    response.body()?.let {
                       var userID= it.user?.id?.let { it1 ->
                           sharedPreferences?.edit()?.putInt("com.sadikahmetozdemir.sadik_fodamy",
                               it1
                           )
                       }
                        println(it.user?.id)

                        var userToken=sharedPreferences?.edit()?.putString("com.sadikahmetozdemir.sadik_fodamy",it.token)
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
