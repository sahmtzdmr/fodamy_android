package com.sadikahmetozdemir.data.shared.utils

import com.sadikahmetozdemir.data.shared.remote.Result

val ERROR_MESSAGE = Result("unknown", "warning")
var NETWORK_ERROR_MESSAGE =
    Result("NO CONNECTION", "warning")
const val BASE_URL = "https://fodamy.mobillium.com/"
val AUTH_LOGIN_SUCCESS = "auth_login_success.json"
val AUTH_LOGIN_ERROR = "auth_login_error.json"
val AUTH_REGISTER_SUCCESS = "auth_login_success.json"
val AUTH_REGISTER_ERROR = "auth_login_success.json"
val AUTH_LOGOUT_ERROR = "auth_logout_success.json"
val FEED_RECIPE_SUCCESS = "feed_recipe_success.json"
val RECIPE_COMMENTS_SUCCESS = "recipe_comments_success.json"
val FAVORITES_RECIPE_SUCCESS = "favorites_recipe_success.json"
val LIKE_REQUEST_SUCCESS = "like_request_success.json"
val USER_FOLLOW_SUCCESS = "user_follow_success.json"
