package com.sadikahmetozdemir.sadik_fodamy.core.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.load

@BindingAdapter("imageLoader")
fun setImageLoader(imageView: ImageView,url:String?){

    imageView.load(url = url)
}
//@BindingAdapter("")