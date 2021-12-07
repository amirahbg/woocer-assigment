package com.app.woocerassignment.fakes

import com.app.woocerassignment.data.signin.remote.SignInRemoteDS

class FakeSignInRemoteDS(private val hasError: Boolean) : SignInRemoteDS {
    override suspend fun signIn(
        name: String,
        email: String,
        website: String,
        username: String,
        password: String
    ): Result<Unit> {
        return if (!hasError)
            Result.success(Unit)
        else
            Result.failure(Throwable("fake"))
    }
}