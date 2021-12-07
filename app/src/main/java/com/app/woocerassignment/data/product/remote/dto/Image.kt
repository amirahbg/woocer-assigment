package com.app.woocerassignment.data.product.remote.dto


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("src")
    val src: String
)