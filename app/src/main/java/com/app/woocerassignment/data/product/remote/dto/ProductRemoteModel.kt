package com.app.woocerassignment.data.product.remote.dto


import com.app.woocerassignment.data.product.local.dto.ProductEntity
import com.google.gson.annotations.SerializedName

data class ProductRemoteModel(
    @SerializedName("average_rating")
    val averageRating: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("name")
    val name: String,
    val price: String,
    @SerializedName("price_html")
    val priceHtml: String,
    @SerializedName("purchasable")
    val purchasable: Boolean,
)

fun ProductRemoteModel.toSummariseEntity() =
    ProductEntity(
        id = id,
        name = name,
        price = price,
        priceHtml = if (priceHtml.isEmpty()) "<p>Not Available</p>" else priceHtml,
        isPurchasable = purchasable,
        image = if (images.isNullOrEmpty()) "" else images[0].src,
        description = description, // is in html format
        rating = averageRating.toFloat()
    )