package com.app.woocerassignment.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface WoocerService {

    @GET
    suspend fun signIn(@Url url: String): Response<Unit>
}