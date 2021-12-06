package com.app.woocerassignment.data.product.remote.dto


import com.google.gson.annotations.SerializedName

data class UrlMeta(
    @SerializedName("origin")
    val origin: Int,
    @SerializedName("position")
    val position: Int
)