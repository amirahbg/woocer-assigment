package com.app.woocerassignment.data.base

import com.app.woocerassignment.data.signin.local.SignInLocalDS
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val localDS: SignInLocalDS
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        if (localDS.getPassword().isNullOrEmpty() ||
            localDS.getUsername().isNullOrEmpty()
        ) {
            return chain.proceed(chain.request())
        }

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("consumer_key", localDS.getUsername())
            .addQueryParameter("consumer_secret", localDS.getPassword())
            .build()

        return chain.proceed(
            original.newBuilder()
                .url(url = url)
                .build()
        )
    }
}