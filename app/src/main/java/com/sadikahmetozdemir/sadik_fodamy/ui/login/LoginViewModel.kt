package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LoginRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.AuthRepository
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val repository: AuthRepository
) : BaseViewModel() {
    val user = MutableLiveData<UserModel>()
    val username = MutableLiveData("")
    val password = MutableLiveData("")

    fun sendLoginRequest() = viewModelScope.launch {
        if (username.value.isNullOrBlank() || password.value.isNullOrBlank()) {
            showMessage(SharedPreferanceStorage.FILL_REQUIRED_FIELDS)
            return@launch
        } else {
            val response =
                repository.loginRequest(
                    LoginRequestModel(
                        username.value.toString(),
                        password.value.toString()
                    )
                )
            when (response?.status) {
                Status.SUCCESS -> {
                    response.data?.let {
                        it.user?.id?.let { it1 ->
                            sharedPreferences.edit()?.putInt(
                                SharedPreferanceStorage.PREFS_USER_ID,
                                it1
                            )?.apply()
                        }
                        sharedPreferences.edit()
                            ?.putString(SharedPreferanceStorage.PREFS_USER_TOKEN, it.token)?.apply()
                        println(it.token)
                        it.user?.let { ituser ->
                            user.postValue(ituser)
                        }
                    }
                    response.message?.let { showToast(it) }
                    navigate(LoginFragmentDirections.toHomeFragment())
                }
                Status.ERROR -> {
                    response.message?.let { showMessage(it) }
                }
                Status.LOADING -> {
                }
                Status.REDIRECT -> {
                }
                null -> {
                }
            }
        }
    }
    fun goForgotPassword() {
        viewModelScope.launch {
            navigate(LoginFragmentDirections.actionLoginFragmentToForgotPassword())
        }
    }
    fun goRegister() {
        viewModelScope.launch {
            navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
    }
}
