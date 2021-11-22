package com.example.e_commerce.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.e_commerce.model.products.Product

@Database(entities = [Product::class],version = 1)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao():ProductsDao
}