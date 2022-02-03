package com.sadikahmetozdemir.sadik_fodamy.ui.home.main

import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.repositories.AuthRepository
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
        sendRequest(request = { authRepository.logoutRequest() },
            success = {
                viewModelScope.launch {
                    it.message?.let { it1 -> showToast(it1) }
                    dataHelperManager.removeToken()
                }
            })

    }
}
