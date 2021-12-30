package com.sadikahmetozdemir.sadik_fodamy.base

import androidx.navigation.NavDirections

sealed class BaseViewEvent {
    data class NavigateTo(val directions: NavDirections) : BaseViewEvent()
    data class ShowMessage(val message: String) : BaseViewEvent()
    object NavigateBack : BaseViewEvent()
}
