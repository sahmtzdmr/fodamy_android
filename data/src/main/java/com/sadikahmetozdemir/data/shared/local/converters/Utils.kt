package com.sadikahmetozdemir.data.shared.local.converters

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Utils

fun<T> type(): Type = object : TypeToken<T>() {}.type

fun <T> toJson(src: Any): String {
    return Gson().toJson(src, type<T>())
}

fun <T> fromJson(src: String): T {
    return Gson().fromJson(src, type<T>())
}