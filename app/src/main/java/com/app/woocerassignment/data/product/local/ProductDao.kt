package com.app.woocerassignment.data.product.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.app.woocerassignment.data.product.local.dto.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM ProductEntity ORDER BY id")
    suspend fun getAllProduct(): List<ProductEntity>?
}