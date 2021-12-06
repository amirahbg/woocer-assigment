package com.app.woocerassignment.data.product.local.dto

data class ProductEntity(
    val id: Int,
    val name: String,
    val price: String,
    val priceHtml: String,
    val isPurchasable: Boolean,
    val image: String,
    val description: String,
    val rating: Float
)