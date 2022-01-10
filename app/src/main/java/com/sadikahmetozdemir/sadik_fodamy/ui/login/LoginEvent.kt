package com.sadikahmetozdemir.sadik_fodamy.ui.login

import androidx.annotation.StringRes

sealed class LoginEvent {
    data class Username(@StringRes val message:Int):LoginEvent()
    data class Email(val message:String):LoginEvent()
    data class Password(val message:String):LoginEvent()
}