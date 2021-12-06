package com.app.woocerassignment.ui

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter("binder:progressVisibility")
fun setProgressBarVisibility(progressBar: ProgressBar, isVisible: Boolean) {
    progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
}