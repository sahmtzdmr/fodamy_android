package com.sadikahmetozdemir.sadik_fodamy.utils.extensions

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sadikahmetozdemir.sadik_fodamy.R

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

fun String.showSnackBar(view: View, colorId: Int = R.color.primary) {
    this.let {
        val snackbar = Snackbar.make(
            view,
            this,
            Snackbar.LENGTH_LONG
        )
        val params: FrameLayout.LayoutParams = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snackbar.setBackgroundTint(
            ContextCompat.getColor(
                view.context,
                colorId
            )
        )
        snackbar.show()
    }
}