package com.sadikahmetozdemir.data.shared.remote

import com.sadikahmetozdemir.data.shared.local.User


data class LoginResponseModel(var token: String?, var user: User?)
