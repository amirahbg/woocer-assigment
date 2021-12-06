package com.app.woocerassignment.data.signin

import kotlinx.coroutines.flow.Flow

interface SignInRepo {
    fun signIn(
        name: String,
        email: String,
        website: String,
        username: String,
        password: String
    ): Flow<Result<Unit>>

    fun isLoggedIn(): Boolean
}