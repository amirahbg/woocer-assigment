package com.app.woocerassignment.data.product.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: String,
    val priceHtml: String,
    val isPurchasable: Boolean,
    val image: String,
    val description: String,
    val rating: Float
)