package com.sadikahmetozdemir.data.shared.utils

import android.content.res.AssetManager
import com.sadikahmetozdemir.data.shared.remote.Result

val ERROR_MESSAGE = Result("unknown", "warning")
var NETWORK_ERROR_MESSAGE =
    Result("NO CONNECTION", "warning")
const val BASE_URL = "https://fodamy.mobillium.com/"
val AUTH_LOGIN_SUCCESS = "auth_login_success.json"
val AUTH_LOGIN_ERROR = "auth_login_error.json"
