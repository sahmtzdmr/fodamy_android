package com.sadikahmetozdemir.sadik_fodamy.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.domain.entities.User
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.data.shared.remote.Status
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
            val response =
                authRepository.loginRequest(
                    com.sadikahmetozdemir.data.shared.remote.LoginRequestModel(
                        username.value.toString(),
                        password.value.toString()
                    )
                )
            when (response?.status) {
                Status.SUCCESS -> {
                    response.data?.let {
                        it.user.id.let { it1 ->
                            dataHelperManager.saveID(it1)
                        }
                        it.token.let { it1 ->
                            dataHelperManager.saveToken(it1)
                        }
                        it.user.let { ituser ->
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
