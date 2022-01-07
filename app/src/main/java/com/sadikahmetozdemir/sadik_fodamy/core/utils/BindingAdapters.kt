package com.sadikahmetozdemir.sadik_fodamy.core.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.load
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.spannableNum

@BindingAdapter("imageLoader")
fun setImageLoader(imageView: ImageView,url:String?){

    imageView.load(url = url)
}
@BindingAdapter("comment_count")
fun setCommentCount(textView: TextView,commentCount:Int){
    textView.text= String.format(textView.context.getString(R.string.comment),commentCount).spannableNum(0,commentCount.toString().length)
}
@BindingAdapter("like_count")
fun setLikeCount(textView: TextView,likeCount:Int){
    textView.text= String.format(textView.context.getString(R.string.like),likeCount).spannableNum(0,likeCount.toString().length)
}