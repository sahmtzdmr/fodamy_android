package com.sadikahmetozdemir.data.shared.remote

import com.sadikahmetozdemir.data.shared.local.UserModel


data class RegisterResponseModel(var token: String?, var user: UserModel?)
