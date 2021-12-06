package com.app.woocerassignment.data.product.remote

import com.app.woocerassignment.data.AuthRequiredWoocerService
import com.app.woocerassignment.data.product.remote.dto.ProductRemoteModel
import javax.inject.Inject

class ProductRemoteDSImpl @Inject constructor(
    private val woocerService: AuthRequiredWoocerService
) : ProductRemoteDS {

    override suspend fun getAllProducts(): Result<List<ProductRemoteModel>> {
        return try {
            val response = woocerService.getAllProducts()
            if (response.isSuccessful) {
                Result.success(response.body() ?: listOf())
            } else {
                Result.failure(Throwable(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}