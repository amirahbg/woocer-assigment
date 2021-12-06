package com.app.woocerassignment.data

import com.app.woocerassignment.data.product.remote.dto.ProductRemoteModel
import retrofit2.Response
import retrofit2.http.GET

interface AuthRequiredWoocerService {
    @GET("/wp-json/wc/v3/products")
    suspend fun getAllProducts(): Response<List<ProductRemoteModel>>
}