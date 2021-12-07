package com.app.woocerassignment.data.signin

import com.app.woocerassignment.data.di.IoDispatcher
import com.app.woocerassignment.data.signin.local.SignInLocalDS
import com.app.woocerassignment.data.signin.remote.SignInRemoteDS
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInRepoImpl @Inject constructor(
    private val localDS: SignInLocalDS,
    private val remoteDS: SignInRemoteDS,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SignInRepo {
    override fun signIn(
        name: String,
        email: String,
        website: String,
        username: String,
        password: String
    ): Flow<Result<Unit>> = flow {
        if (!isValidInput(website, username, password)) {
            emit(Result.failure(Throwable("Invalid Input")))
            return@flow
        }
        val response = remoteDS.signIn(
            name, email, website, username, password
        )
        if (response.isSuccess) {
            localDS.saveSignIn(username, password, website)
        }
        emit(response)
    }.flowOn(ioDispatcher)
        .catch { emit(Result.failure(it)) }

    private fun isValidInput(website: String, username: String, password: String): Boolean {
        return website.isNotBlank() && username.isNotBlank() && password.isNotBlank()
    }

    override fun isLoggedIn(): Boolean {
        return localDS.isLoggedIn()
    }
}