package com.app.woocerassignment.data.product.local

import com.app.woocerassignment.data.product.local.dto.ProductEntity

interface ProductLocalDS {
    suspend fun saveProducts(products: List<ProductEntity>)

    suspend fun getAllProducts(): List<ProductEntity>?
}
