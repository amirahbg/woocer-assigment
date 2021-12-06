package com.app.woocerassignment.data.di

import javax.inject.Qualifier

//region Dispatchers
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher
//endregion

//region Service
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRequired

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoAuthRequired
//endregion
