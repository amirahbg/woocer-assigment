package com.app.woocerassignment.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.woocerassignment.data.product.local.ProductDao
import com.app.woocerassignment.data.product.local.dto.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}