package com.app.woocerassignment.data.product

import com.app.woocerassignment.data.di.IoDispatcher
import com.app.woocerassignment.data.product.local.dto.ProductEntity
import com.app.woocerassignment.data.product.remote.ProductRemoteDS
import com.app.woocerassignment.data.product.remote.dto.toSummariseEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepoImpl @Inject constructor(
    private val remoteDS: ProductRemoteDS,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ProductRepo {

    override suspend fun getAllProducts(): Flow<Result<List<ProductEntity>>> =
        flow {
            val response = remoteDS.getAllProducts()
            emit(Result.success(response.getOrThrow().map { it.toSummariseEntity() }))
        }
            .flowOn(dispatcher)
            .catch { emit(Result.failure(it)) }
}