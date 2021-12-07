package com.app.woocerassignment.data.di

import com.app.woocerassignment.data.product.ProductRepo
import com.app.woocerassignment.data.product.ProductRepoImpl
import com.app.woocerassignment.data.product.local.ProductLocalDS
import com.app.woocerassignment.data.product.local.ProductLocalDSImpl
import com.app.woocerassignment.data.product.remote.ProductRemoteDS
import com.app.woocerassignment.data.product.remote.ProductRemoteDSImpl
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

    @Binds
    abstract fun bindProductRemoteDS(impl: ProductRemoteDSImpl): ProductRemoteDS

    @Binds
    abstract fun bindProductRepo(impl: ProductRepoImpl): ProductRepo

    @Binds
    abstract fun bindProductLocalDS(impl: ProductLocalDSImpl): ProductLocalDS
}