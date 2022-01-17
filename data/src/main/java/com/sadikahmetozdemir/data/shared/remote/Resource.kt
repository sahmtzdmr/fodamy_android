package com.sadikahmetozdemir.data.shared.remote

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val code: String = String()
) {
    companion object {

        fun <T> success(data: T? = null): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> redirect(data: T? = null): Resource<T> {
            return Resource(
                Status.REDIRECT,
                data,
                null
            )
        }

        fun <T> error(errorMessage: Result, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                errorMessage.error,
                errorMessage.code
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    REDIRECT
}
