package com.sadikahmetozdemir.sadik_fodamy.shared.remote

import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel

data class LoginResponseModel(var token:String?, var user :UserModel?)