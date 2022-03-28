package com.sadikahmetozdemir.data.shared.exceptions

data class SimpleHttpException(
    val code: String?,
    val friendlyMessage: String?
) : Exception()
