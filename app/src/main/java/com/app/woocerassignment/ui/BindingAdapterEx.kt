package com.app.woocerassignment.ui

import android.os.Build
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
    imageView: AppCompatImageView,
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

@BindingAdapter("binder:priceText")
fun setPriceToTextView(textView: TextView, priceHtml: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        textView.text = Html.fromHtml(
            priceHtml,
            Html.FROM_HTML_MODE_COMPACT
        )

    } else textView.text = Html.fromHtml(priceHtml)
}

@BindingAdapter("binder:isRefreshing")
fun setIsRefreshing(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: Boolean) {
    Log.i("TAG", "setIsRefreshing: $isRefreshing")
    swipeRefreshLayout.isRefreshing = isRefreshing
}