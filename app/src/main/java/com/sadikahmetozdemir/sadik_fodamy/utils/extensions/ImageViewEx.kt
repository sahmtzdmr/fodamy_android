package com.sadikahmetozdemir.sadik_fodamy.utils.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sadikahmetozdemir.sadik_fodamy.R

fun ImageView.load(isFadeInEnabled: Boolean = true, url: String?) {
    val duration = if (isFadeInEnabled)50 else 0
    Glide
        .with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade(duration))
        .into(this)
}
fun ImageView.loadCircleCrop(isFadeInEnabled: Boolean = true, url: String?) {
    val duration = if (isFadeInEnabled)50 else 0

    Glide
        .with(context)
        .load(url)
        .circleCrop()
        .placeholder(R.drawable.profile)
        .transition(DrawableTransitionOptions.withCrossFade(duration))
        .into(this)
}
