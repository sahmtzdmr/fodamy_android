package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.content.SharedPreferences
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Resource
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.AuthRepository
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val retrofit: Retrofit,
    private val repository: AuthRepository
) : ViewModel() {


    val showUsernameError = MutableLiveData<String>()
    val showEmailError = MutableLiveData<String>()
    val showPasswordError = MutableLiveData<String>()
    val showErrorMessage = MutableLiveData<String?>()

    override fun onCleared() {
        super.onCleared()

    }

    fun validateFile(username: String, email: String, password: String): Boolean {


        if (username.isEmpty()) {
            //binding?.textInputLayoutUsername?.error = "Kullanıcı adı kısmı boş bırakılamaz."
            showUsernameError.postValue("Kullanıcı adı kısmı boş bırakılamaz.")
            return false

        }


        if (email.isEmpty()) {
            //binding?.textInputLayoutEmail?.error = "Email kısmı boş bırakılamaz."
            showEmailError.postValue("Email kısmı boş bırakılamaz.")
            return false
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            showEmailError.postValue("Geçerli bir email giriniz.")
        }
        if (password.isEmpty()) {
            //binding?.textInputPassword?.error = "Şifre kısmı boş bırakılamaz."
            showPasswordError.postValue("Şifre kısmı boş bırakılamaz.")
            return false
        }


        return true


    }
    /*
    fun sendRegisterRequest(
        username: String?,
        email: String?,
        password: String?,
        name: String?,
        surname: String?
    ) {
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

     */


    suspend fun sendRegisterRequest(
        username: String?,
        email: String?,
        password: String?,
        name: String?,
        surname: String?
    ): Resource<RegisterResponseModel>? {
        val response = repository.registerRequest(
            RegisterRequestModel(
                username,
                email,
                password,
                name,
                surname
            )
        )
        when (response?.status) {
            Status.SUCCESS -> {
                response?.data.let {
                    var userID = it?.user?.id?.let {
                        sharedPreferences.edit().putInt(SharedPreferanceStorage.PREFS_USER_ID, it)
                            ?.apply()
                    }
                    var userToken = it?.token.let {
                        sharedPreferences.edit()
                            .putString(SharedPreferanceStorage.PREFS_USER_TOKEN, it).apply()
                    }
                }

            }
            Status.ERROR -> {
                showErrorMessage.postValue(response.message)

            }
        }
        return response

    }
}