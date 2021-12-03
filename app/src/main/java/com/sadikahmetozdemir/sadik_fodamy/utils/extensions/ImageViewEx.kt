package com.sadikahmetozdemir.sadik_fodamy.utils.extensions

import android.transition.Fade
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel

fun ImageView.load(isFadeInEnabled:Boolean=true,url: String?){
val duration=if(isFadeInEnabled)50 else    0
    Glide
        .with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade(duration))
        .into(this)


}
fun ImageView.loadCircleCrop(isFadeInEnabled:Boolean=true,url: String?){
    val duration=if(isFadeInEnabled)50 else    0

    Glide
        .with(context)
        .load(url)
        .circleCrop()
        .placeholder(R.drawable.ic_baseline_android_24)
        .transition(DrawableTransitionOptions.withCrossFade(duration))
        .into(this)
}
