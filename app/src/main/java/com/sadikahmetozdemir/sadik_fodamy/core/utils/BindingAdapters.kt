package com.sadikahmetozdemir.sadik_fodamy.core.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.load
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.loadCircleCrop
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.spannableNum
import java.util.*

@BindingAdapter("imageLoader")
fun setImageLoader(imageView: ImageView, url: String?) {

    imageView.load(url = url)
}

@BindingAdapter("comment_count")
fun setCommentCount(textView: TextView, commentCount: Int) {
    textView.text = String.format(textView.context.getString(R.string.comment), commentCount)
        .spannableNum(0, commentCount.toString().length)
}

@BindingAdapter("like_count")
fun setLikeCount(textView: TextView, likeCount: Int) {
    textView.text = String.format(textView.context.getString(R.string.like), likeCount)
        .spannableNum(0, likeCount.toString().length)
}

@BindingAdapter("diffenrenceTime")
fun setDifferenceTime(textView: TextView, difference: Int) {
    textView.text = String.format(textView.context.getString(R.string.time), difference)

}

@BindingAdapter("circleImageLoader")
fun setCircleImageLoader(imageView: ImageView, url: String?) {

    imageView.loadCircleCrop(url = url)
}

@BindingAdapter("following_count")
fun setFollowingCount(textView: TextView, followingCount: Int) {
    textView.text = String.format(textView.context.getString(R.string.follower), followingCount)
}

@BindingAdapter("recipe_count")
fun setRecipeCount(textView: TextView, recipeCount: Int) {
    textView.text = String.format(textView.context.getString(R.string.recipe), recipeCount)
}

@BindingAdapter("toUpperCase")
fun toUpperCase(textView: TextView, text: String?) {
    val turkishLocale = Locale.forLanguageTag("tr")
    textView.text=text?.uppercase(turkishLocale)

}
