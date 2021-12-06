package com.app.woocerassignment.data.product.remote.dto


import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: Any
)