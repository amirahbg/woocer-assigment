package com.app.woocerassignment.data

import com.app.woocerassignment.data.product.remote.dto.ProductRemoteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface NoAuthRequiredWoocerService {

    @GET
    suspend fun signIn(@Url url: String): Response<Unit>

    @GET
    suspend fun getAllProducts(): Response<List<ProductRemoteModel>>
}