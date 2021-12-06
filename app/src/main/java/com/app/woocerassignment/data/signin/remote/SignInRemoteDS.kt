package com.app.woocerassignment.data.signin.remote

interface SignInRemoteDS {
    suspend fun signIn(
        name: String,
        email: String,
        website: String,
        username: String,
        password: String
    ): Result<Unit>
}