package com.app.woocerassignment.fakes

import com.app.woocerassignment.data.product.local.ProductLocalDS
import com.app.woocerassignment.data.product.local.dto.ProductEntity

class FakeProductLocalDS(
    private var products: List<ProductEntity>?,
    private val hasError: Boolean
) : ProductLocalDS {
    override suspend fun saveProducts(products: List<ProductEntity>) {

    }

    override suspend fun getAllProducts(): List<ProductEntity>? {
        return if (hasError)
            throw RuntimeException("Fake")
        else
            products
    }
}