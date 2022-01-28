package com.sadikahmetozdemir.sadik_fodamy.ui.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.RegisterRequest
import com.sadikahmetozdemir.domain.entities.User
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val dataHelperManager: DataHelperManager,
    private val authRepository: AuthRepository
) : BaseViewModel() {

    val username = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val user = MutableLiveData<User>()

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
            sendRequest(request = {
                authRepository.registerRequest(
                    RegisterRequest(
                        username = username.value,
                        email = email.value,
                        password = password.value,
                    )
                )
            },
                success = {
                    viewModelScope.launch {
                        it.user?.id?.let { it1 ->
                            dataHelperManager.saveID(it1)
                            it.token?.let { it2 -> dataHelperManager.saveToken(it2) }
                            navigate(SignUpFragmentDirections.toHomeFragment())

                        }
                    }
                }
            )
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
