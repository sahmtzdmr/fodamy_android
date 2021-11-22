package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LoginRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LoginResponseModel
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val sharedPreferences:SharedPreferences,private val retrofit: Retrofit) :ViewModel() {


    val showEmailError =MutableLiveData<String>()
    val showPasswordError =MutableLiveData<String>()

    init {

    }
    override fun onCleared() {
        super.onCleared()

    }
     fun validateFields(email:String,password:String): Boolean {

        if (email.isEmpty()) {
          //  binding?.textInputLayoutEmail?.error = "Email alanı boş kalamaz."

              showEmailError.postValue("Email alanı boş kalamaz.")
            return false
        }
        if (password.isEmpty()) {
          // binding?.textInputLayoutPassword?.error = "Şifre alanı boş kalamaz."
            showPasswordError.postValue("Şifre alanı boş kalamaz.")
            return false
        }

        return true
    }


    fun sendLoginRequest(username: String, password: String) {

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
                            )?.apply()
                        }
                        println(it.user?.id)

                        var userToken = sharedPreferences?.edit()
                            ?.putString(SharedPreferanceStorage.PREFS_USER_TOKEN, it.token)?.apply()
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