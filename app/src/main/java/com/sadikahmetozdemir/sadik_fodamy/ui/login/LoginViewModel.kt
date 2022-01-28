package com.sadikahmetozdemir.sadik_fodamy.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.LoginRequest
import com.sadikahmetozdemir.domain.entities.User
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataHelperManager: DataHelperManager
) : BaseViewModel() {
    val user = MutableLiveData<User>()
    val username = MutableLiveData("")
    val password = MutableLiveData("")

    fun sendLoginRequest() = viewModelScope.launch {
        if (username.value.isNullOrBlank() || password.value.isNullOrBlank()) {
            showMessage(SharedPreferanceStorage.FILL_REQUIRED_FIELDS)
            return@launch
        } else {
            sendRequest(request = {
                authRepository.loginRequest(
                    LoginRequest(
                        username.value.toString(),
                        password.value.toString()
                    )
                )
            },
                success = {
                    viewModelScope.launch {
                        it?.token?.let { it1 -> dataHelperManager.saveToken(it1) }
                        it?.user?.id?.let { it1 -> dataHelperManager.saveID(it1) }
                        navigate(LoginFragmentDirections.toHomeFragment())
                    }

                })
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
