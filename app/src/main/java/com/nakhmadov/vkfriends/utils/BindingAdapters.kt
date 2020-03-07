package com.nakhmadov.vkfriends.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}

@BindingAdapter("setNullText")
fun TextView.setNullText(text: String?) {
    this.text = text ?: "Unknown city"
}