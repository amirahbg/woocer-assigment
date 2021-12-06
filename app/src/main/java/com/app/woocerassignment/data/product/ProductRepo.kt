package com.app.woocerassignment.data.product

import com.app.woocerassignment.data.product.local.dto.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepo {
    suspend fun getAllProducts(): Flow<Result<List<ProductEntity>>>
}