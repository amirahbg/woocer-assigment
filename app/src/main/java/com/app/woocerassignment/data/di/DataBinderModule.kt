package com.app.woocerassignment.data.di

import com.app.woocerassignment.data.signin.SignInRepo
import com.app.woocerassignment.data.signin.SignInRepoImpl
import com.app.woocerassignment.data.signin.local.SignInLocalDS
import com.app.woocerassignment.data.signin.local.SignInLocalDSImpl
import com.app.woocerassignment.data.signin.remote.SignInRemoteDS
import com.app.woocerassignment.data.signin.remote.SignInRemoteDSImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBinderModule {

    @Binds
    abstract fun bindSignInRemote(impl: SignInRemoteDSImpl): SignInRemoteDS

    @Binds
    abstract fun bindSignInRepo(impl: SignInRepoImpl): SignInRepo

    @Binds
    abstract fun bindSignInLocalDS(impl: SignInLocalDSImpl): SignInLocalDS
}