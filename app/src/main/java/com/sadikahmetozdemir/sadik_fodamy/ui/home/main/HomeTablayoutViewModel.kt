package com.sadikahmetozdemir.sadik_fodamy.ui.home.main

import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.core.utils.DataHelperManager
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.AuthRepository
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
