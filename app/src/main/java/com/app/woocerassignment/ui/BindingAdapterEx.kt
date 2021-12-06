package com.app.woocerassignment.ui

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.app.woocerassignment.R
import com.bumptech.glide.Glide

@BindingAdapter("binder:progressVisibility")
fun setProgressBarVisibility(
    progressBar: ProgressBar,
    isVisible: Boolean
) {
    progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("binder:imageUrl")
fun loadImageUrlIntoImageView(
    imageView: ImageView,
    imageUrl: String
) {
    Glide
        .with(imageView.context)
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .into(imageView)
}