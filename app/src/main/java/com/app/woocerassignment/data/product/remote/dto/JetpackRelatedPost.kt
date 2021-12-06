package com.app.woocerassignment.data.product.remote.dto


import com.google.gson.annotations.SerializedName

data class JetpackRelatedPost(
    @SerializedName("classes")
    val classes: List<Any>,
    @SerializedName("context")
    val context: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("excerpt")
    val excerpt: String,
    @SerializedName("format")
    val format: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: Img,
    @SerializedName("rel")
    val rel: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("url_meta")
    val urlMeta: UrlMeta
)