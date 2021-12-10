package com.sadikahmetozdemir.sadik_fodamy.utils.extensions

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

fun String.spannableNum(start: Int, end: Int): SpannableString {
    val spannable = SpannableString(this)
    spannable.setSpan(
        ForegroundColorSpan(Color.RED),
        start,
        end,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannable
}