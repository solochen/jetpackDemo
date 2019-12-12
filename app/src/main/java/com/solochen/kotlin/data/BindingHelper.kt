package com.solochen.kotlin.data

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun bindImageNetUrl(view: ImageView, url: String) {
    if (url.isNullOrEmpty()) return
    Glide.with(view.context)
        .asBitmap()
        .load(url)
        .centerCrop()
        .into(view)
}