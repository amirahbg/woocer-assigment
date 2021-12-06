package com.app.woocerassignment.data.signin.local

interface SignInLocalDS {
    fun isLoggedIn(): Boolean

    fun saveSignIn(
        username: String,
        password: String,
        website: String
    )

    fun getWebsite(): String?

    fun getUsername(): String?

    fun getPassword(): String?
}