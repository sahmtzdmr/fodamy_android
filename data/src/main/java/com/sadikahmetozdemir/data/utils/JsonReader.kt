package com.sadikahmetozdemir.data.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class JsonReader @Inject constructor(@ApplicationContext private val applicationContext: Context) {
    fun readJson(fileName: String): String {
        val jsonData: String =
            applicationContext.assets.open(fileName.trim()).bufferedReader().use {
                it.readLine()
            }
        return jsonData
    }
}