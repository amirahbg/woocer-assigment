package com.app.woocerassignment.data.signin.local

import android.content.SharedPreferences
import javax.inject.Inject

class SignInLocalDSImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SignInLocalDS {

    override fun isLoggedIn(): Boolean {
        return !sharedPreferences.getString(USERNAME_KEY, null).isNullOrEmpty() &&
                !sharedPreferences.getString(PASSWORD_KEY, null).isNullOrEmpty() &&
                !sharedPreferences.getString(WEBSITE_KEY, null).isNullOrEmpty()
    }

    override fun saveSignIn(username: String, password: String, website: String) {
        sharedPreferences.edit()
            .putString(USERNAME_KEY, username)
            .putString(PASSWORD_KEY, password)
            .putString(WEBSITE_KEY, website)
            .apply()
    }

    override fun getWebsite(): String? {
        return sharedPreferences.getString(WEBSITE_KEY, null)
    }

    override fun getUsername(): String? {
        return sharedPreferences.getString(USERNAME_KEY, null)
    }

    override fun getPassword(): String? {
        return sharedPreferences.getString(PASSWORD_KEY, null)
    }

    companion object {
        const val USERNAME_KEY = "username-key"
        const val PASSWORD_KEY = "password-key"
        const val WEBSITE_KEY = "website-key"
    }
}