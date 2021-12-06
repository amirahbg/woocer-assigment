package com.app.woocerassignment.data.product.remote.dto


import com.google.gson.annotations.SerializedName

data class Img(
    @SerializedName("alt_text")
    val altText: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("src")
    val src: String,
    @SerializedName("width")
    val width: Int
)