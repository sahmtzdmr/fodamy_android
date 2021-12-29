package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.content.SharedPreferences
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Resource
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.AuthRepository
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val repository: AuthRepository
) : BaseViewModel() {

    val username = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val showUsernameError = MutableLiveData<String>()
    val showEmailError = MutableLiveData<String>()
    val showPasswordError = MutableLiveData<String>()
    val showErrorMessage = MutableLiveData<String?>()
    val user=MutableLiveData<UserModel>()

    fun sendRegisterRequest() = viewModelScope.launch {
        if (!validateFile(
                username.value.toString(),
                email.value.toString(),
                password.value.toString()
            )
        ) {
            showMessage(SharedPreferanceStorage.FILL_REQUIRED_FIELDS)
            return@launch
        } else {
            val response = repository.registerRequest(
                RegisterRequestModel(
                    username = username.value,
                    email = email.value,
                    password = password.value,
                )
            )
            when (response?.status) {
                Status.SUCCESS -> {
                    response?.data.let {
                        it?.user?.id?.let {
                            sharedPreferences.edit()
                                .putInt(SharedPreferanceStorage.PREFS_USER_ID, it)
                                ?.apply()
                        }
                        it?.token.let {
                            sharedPreferences.edit()
                                .putString(SharedPreferanceStorage.PREFS_USER_TOKEN, it).apply()

                        }
                        it?.user.let {ituser->
                            user.postValue(ituser)


                        }
                    }


                    //Geri ekranına yollar
                    navigate(SignUpFragmentDirections.toHomeFragment())
                }
                Status.ERROR -> {
                    response.message?.let {
                        showMessage(it)
                    }

                }
            }
        }
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
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showEmailError.postValue("Geçerli bir email giriniz.")
        }
        if (password.isEmpty()) {
            //binding?.textInputPassword?.error = "Şifre kısmı boş bırakılamaz."
            showPasswordError.postValue("Şifre kısmı boş bırakılamaz.")
            return false
        }


        return true


    }

    fun goLogin() {
        viewModelScope.launch {
            navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }
    }


}