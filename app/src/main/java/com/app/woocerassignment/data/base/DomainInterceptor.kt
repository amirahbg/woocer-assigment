package com.app.woocerassignment.data.base

import android.content.SharedPreferences
import com.app.woocerassignment.data.signin.local.SignInLocalDS
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DomainInterceptor @Inject constructor(
    private val localDS: SignInLocalDS
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = localDS.getWebsite()
        newUrl?.let {
            return chain.proceed(
                request.newBuilder()
                    .url(
                        request.url.toString()
                            .replace("https://localhost/", it)
                            .toHttpUrlOrNull() ?: request.url
                    )
                    .build()
            )
        }
        return chain.proceed(request = request)
    }
}