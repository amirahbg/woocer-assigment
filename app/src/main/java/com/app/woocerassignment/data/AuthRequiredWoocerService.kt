package com.app.woocerassignment.data

import com.app.woocerassignment.data.product.remote.dto.ProductRemoteModel
import retrofit2.Response

interface AuthRequiredWoocerService {
    suspend fun getAllProducts(): Response<List<ProductRemoteModel>>
}