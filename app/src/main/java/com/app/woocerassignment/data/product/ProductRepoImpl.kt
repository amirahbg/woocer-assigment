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
        flow<Result<List<ProductEntity>>> {
            val response = remoteDS.getAllProducts()
            response
                .onSuccess {
                    emit(Result.success(it.map { remoteModel -> remoteModel.toSummariseEntity() }))
                }
                .onFailure {
                    emit(Result.failure(it))
                }
        }
            .flowOn(dispatcher)
            .catch { emit(Result.failure(it)) }
}