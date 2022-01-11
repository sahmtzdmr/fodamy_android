package com.sadikahmetozdemir.sadik_fodamy.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.sadikahmetozdemir.sadik_fodamy.utils.SingleLiveEvent
import kotlinx.coroutines.launch

abstract class BaseViewModel constructor() : ViewModel() {
    val baseEvent = SingleLiveEvent<BaseViewEvent>()

    fun navigate(directions: NavDirections) = viewModelScope.launch {
        baseEvent.postValue(BaseViewEvent.NavigateTo(directions))
    }

    fun showMessage(message: String) = viewModelScope.launch {
        if (message.isBlank())
            return@launch
        baseEvent.postValue(BaseViewEvent.ShowMessage(message))
    }
    fun popBackStack() {
        baseEvent.postValue(BaseViewEvent.NavigateBack)
    }
    fun showToast(message: String) = viewModelScope.launch {
        if (message.isBlank())
            return@launch
        baseEvent.postValue(BaseViewEvent.ShowToast(message))
    }
}
