package com.app.woocerassignment.data.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.app.woocerassignment.data.AuthRequiredWoocerService
import com.app.woocerassignment.data.NoAuthRequiredWoocerService
import com.app.woocerassignment.data.base.AppDatabase
import com.app.woocerassignment.data.base.AuthorizationInterceptor
import com.app.woocerassignment.data.base.DomainInterceptor
import com.app.woocerassignment.data.product.local.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataProviderModule {

    @Singleton
    @Provides
    @NoAuthRequired
    fun provideNoAuthOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    @AuthRequired
    fun provideAuthOkHttpClient(
        domainInterceptor: DomainInterceptor,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(domainInterceptor)
            .addInterceptor(authorizationInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideNoAuthWoocerService(
        @NoAuthRequired okHttpClient: OkHttpClient
    ): NoAuthRequiredWoocerService {
        return Retrofit.Builder()
            .baseUrl("https://localhost/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(NoAuthRequiredWoocerService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthWoocerService(
        @AuthRequired okHttpClient: OkHttpClient
    ): AuthRequiredWoocerService {
        return Retrofit.Builder()
            .baseUrl("https://localhost/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(AuthRequiredWoocerService::class.java)
    }

    @Singleton
    @Provides
    fun provideSharedPreference(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("woocer", MODE_PRIVATE)
    }

    @Singleton
    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "woocer.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }
}