package com.app.woocerassignment.fakes

import com.app.woocerassignment.data.product.remote.ProductRemoteDS
import com.app.woocerassignment.data.product.remote.dto.ProductRemoteModel

class FakeProductRemoteDS(
    private var products: List<ProductRemoteModel>,
    private val hasError: Boolean
) : ProductRemoteDS {
    override suspend fun getAllProducts(): Result<List<ProductRemoteModel>> {
        return if (hasError) {
            Result.failure(Throwable("fake"))
        } else
            Result.success(products)
    }
}