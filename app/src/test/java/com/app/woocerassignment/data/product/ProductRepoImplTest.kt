package com.app.woocerassignment.data.product

import com.app.woocerassignment.data.product.local.dto.ProductEntity
import com.app.woocerassignment.data.product.remote.dto.Image
import com.app.woocerassignment.data.product.remote.dto.ProductRemoteModel
import com.app.woocerassignment.data.product.remote.dto.toSummariseEntity
import com.app.woocerassignment.fakes.FakeProductLocalDS
import com.app.woocerassignment.fakes.FakeProductRemoteDS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class ProductRepoImplTest {

    private val localData = fakeLocalData()
    private val remoteData = fakeRemoteData()

    @Test
    fun `getAllProduct returned from remote when local data is empty`() = runBlockingTest {
        var sut = ProductRepoImpl(
            FakeProductLocalDS(null, false),
            FakeProductRemoteDS(remoteData, false),
            Dispatchers.Unconfined
        )

        var response = sut.getAllProducts()
        var result = response.toList()

        assertEquals(result.first(), Result.success(remoteData.map { it.toSummariseEntity() }))

        sut = ProductRepoImpl(
            FakeProductLocalDS(listOf(), false),
            FakeProductRemoteDS(remoteData, false),
            Dispatchers.Unconfined
        )

        response = sut.getAllProducts()
        result = response.toList()

        assertEquals(result.first(), Result.success(remoteData.map { it.toSummariseEntity() }))
    }

    @Test
    fun `getAllProduct returned from remote when local data has error`() = runBlockingTest {
        val sut = ProductRepoImpl(
            FakeProductLocalDS(localData, true),
            FakeProductRemoteDS(remoteData, false),
            Dispatchers.Unconfined
        )

        val response = sut.getAllProducts()
        val result = response.toList()

        assertEquals(result.first(), Result.success(remoteData.map { it.toSummariseEntity() }))
    }

    @Test
    fun `getAllProduct returned from local when local data is not empty`() = runBlockingTest {
        val sut = ProductRepoImpl(
            FakeProductLocalDS(localData, false),
            FakeProductRemoteDS(remoteData, false),
            Dispatchers.Unconfined
        )

        val response = sut.getAllProducts()
        val result = response.toList()

        assertEquals(result.first(), Result.success(localData))
    }

    private fun fakeRemoteData(): List<ProductRemoteModel> {
        return (0..10).map {
            ProductRemoteModel(
                (Random.nextFloat() * 5).toString(),
                "description #$it",
                it,
                listOf(Image(it, "", "")),
                "name #$it",
                it.toString(),
                it.toString(),
                it % 2 == 0
            )
        }
    }

    private fun fakeLocalData(): List<ProductEntity>? {
        return (11..20).map {
            ProductEntity(
                it,
                "name #$it",
                it.toString(),
                it.toString(),
                it % 2 == 0,
                "image #$it",
                "description #$it",
                rating = Random.nextFloat() * 5
            )
        }
    }
}