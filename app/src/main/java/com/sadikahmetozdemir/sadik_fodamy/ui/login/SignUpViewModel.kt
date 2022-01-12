package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.core.utils.DataHelperManager
import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.AuthRepository
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val dataHelperManager: DataHelperManager,
    private val repository: AuthRepository
) : BaseViewModel() {

    val username = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val user = MutableLiveData<UserModel>()

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
            when (response.status) {
                Status.SUCCESS -> {
                    response.data.let {
                        it?.user?.id?.let { it1 ->
                            dataHelperManager.saveID(it1)
                        }
                        it?.token?.let { it1 ->
                            dataHelperManager.saveToken(it1)
                        }
                        it?.user.let { ituser ->
                            user.postValue(ituser)
                        }
                        response.message?.let { it1 -> showToast(it1) }
                    }
                    navigate(SignUpFragmentDirections.toHomeFragment())
                }
                Status.ERROR -> {
                    response.message?.let {
                        showMessage(it)
                    }
                }
                Status.LOADING -> {}
                Status.REDIRECT -> {}
            }
        }
    }

    private fun validateFile(username: String, email: String, password: String): Boolean {
        if (username.isEmpty()) {
            return false
        }
        if (email.isEmpty()) {
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }
        if (password.isEmpty()) {
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
