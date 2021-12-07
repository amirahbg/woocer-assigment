package com.app.woocerassignment.fakes

import com.app.woocerassignment.data.signin.local.SignInLocalDS

class FakeSignInLocalDS(
    var usernameX: String?,
    var passwordX: String?,
    var websiteX: String?
) : SignInLocalDS {
    override fun isLoggedIn(): Boolean {
        return !(usernameX.isNullOrBlank() || passwordX.isNullOrBlank() || websiteX.isNullOrBlank())
    }

    override fun saveSignIn(username: String, password: String, website: String) {
        usernameX = username
        passwordX = password
        websiteX = website
    }

    override fun getWebsite(): String? {
        return websiteX
    }

    override fun getUsername(): String? {
        return usernameX
    }

    override fun getPassword(): String? {
        return passwordX
    }
}