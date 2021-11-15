package com.sadikahmetozdemir.sadik_fodamy.shared.remote

data class RegisterRequestModel(
    var email: String?,
    var password: String?,
    var username: String?,
    var name: String?,
    var surname: String?

)