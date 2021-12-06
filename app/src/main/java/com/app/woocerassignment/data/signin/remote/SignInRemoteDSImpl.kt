package com.app.woocerassignment.data.signin.remote

import com.app.woocerassignment.data.WoocerService
import kotlinx.coroutines.delay
import javax.inject.Inject

class SignInRemoteDSImpl @Inject constructor(
    private val woocerService: WoocerService
) : SignInRemoteDS {

    override suspend fun signIn(
        name: String,
        email: String,
        website: String,
        username: String,
        password: String
    ): Result<Unit> =
        try {
            val url =
                "${website}wp-json/wc/v3/" +
                        "?consumer_key=$username" +
                        "&consumer_secret=$password" +
                        "&name=$name" +
                        "&email=$email"
            val result = woocerService.signIn(url)
            if (result.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(RuntimeException())
            }
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
}