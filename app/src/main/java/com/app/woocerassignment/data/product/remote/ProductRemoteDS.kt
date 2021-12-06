package com.app.woocerassignment.data.product.remote

import com.app.woocerassignment.data.product.remote.dto.ProductRemoteModel

interface ProductRemoteDS {
    suspend fun getAllProducts(): Result<List<ProductRemoteModel>>
}