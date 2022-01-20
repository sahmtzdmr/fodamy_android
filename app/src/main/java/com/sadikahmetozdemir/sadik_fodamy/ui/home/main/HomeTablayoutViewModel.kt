package com.sadikahmetozdemir.sadik_fodamy.ui.home.main

import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import com.sadikahmetozdemir.domain.requests.Status
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeTablayoutViewModel @Inject constructor(
    private var authRepository: AuthRepository,
    private val dataHelperManager: DataHelperManager
) : BaseViewModel() {
    fun logoutRequest() {
        viewModelScope.launch {
            val response = authRepository.logoutRequest()
            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.message?.let {
                        showToast(it)
                    }
                    dataHelperManager.removeToken()


                }
                Status.ERROR -> {

                    response.data?.message?.let { showToast(it) }
                }
                Status.LOADING -> {
                }
                Status.REDIRECT -> {
                }
            }
        }
    }
}
