package com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.AuthRepository
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeTablayoutViewModel @Inject constructor(
    private var authRepository: AuthRepository,
    private var sharedPreferences: SharedPreferences
) : ViewModel() {
    val event = MutableLiveData<HomeTablayoutEvent>()

    fun logoutRequest() {
        viewModelScope.launch {
            val response = authRepository.logoutRequest()
            when (response?.status) {
                Status.SUCCESS -> {
                    sharedPreferences.edit().remove(SharedPreferanceStorage.PREFS_USER_TOKEN).apply()
                    event.postValue(response.data?.message?.let { HomeTablayoutEvent.ShowMassage(it) })
                }

                Status.ERROR -> {

                    event.postValue(response.data?.message?.let { HomeTablayoutEvent.ShowMassage(it) })
                }
            }
        }
    }
}
