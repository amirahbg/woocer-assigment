package com.app.woocerassignment.data.product.local

import com.app.woocerassignment.data.product.local.dto.ProductEntity
import javax.inject.Inject

class ProductLocalDSImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductLocalDS {

    override suspend fun saveProducts(products: List<ProductEntity>) {
        productDao.saveProducts(products = products)
    }

    override suspend fun getAllProducts(): List<ProductEntity>? {
        return productDao.getAllProduct()
    }
}