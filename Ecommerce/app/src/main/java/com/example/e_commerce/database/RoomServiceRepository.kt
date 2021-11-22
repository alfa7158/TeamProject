package com.example.e_commerce.database

import android.content.Context
import androidx.room.Room
import com.example.e_commerce.model.products.Product

private const val DATABASE_NAME = "products-database"
class RoomServiceRepository(context: Context) {

    private val database = Room.databaseBuilder(
        context,
        ProductDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    private val productsDao = database.productDao()

    suspend fun insertProducts(products: List<Product>) =
        productsDao.insertProducts(products)

    suspend fun getProducts() = productsDao.getProducts()

    companion object {
        private var instance: RoomServiceRepository? = null

        fun init(context: Context) {
            if (instance == null)
                instance = RoomServiceRepository(context)
        }

        fun get(): RoomServiceRepository {
            return instance ?: throw Exception("Room Service Repository must be initialized")
        }
    }
}