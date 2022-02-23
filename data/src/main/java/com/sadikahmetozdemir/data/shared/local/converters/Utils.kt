package com.sadikahmetozdemir.data.shared.local.converters

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Utils

inline fun <reified T> type(): Type = object : TypeToken<T>() {}.type

inline fun <reified T> toJson(src: Any): String {
    return Gson().toJson(src, type<T>())
}

inline fun <reified T> fromJson(src: String): T {
    return Gson().fromJson(src, type<T>())
}