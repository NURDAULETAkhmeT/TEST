package com.example.arbuxxx.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.arbuxxx.domain.model.Product
import com.example.arbuxxx.domain.model.ProductBasket

@Dao
interface ProductDao {

    @Upsert
    suspend fun insertProduct(product: Product)

    // Добавьте метод для вставки списка продуктов
    @Upsert
    suspend fun insertAllProducts(products: List<Product>)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>

    suspend fun addItemProduct(product: Product) {
        if (product.totalQuantity < product.quantity){
            product.totalQuantity++
            insertProduct(product)
        }
    }

    suspend fun deleteItemProduct(product: Product) {
        product.totalQuantity--
        insertProduct(product)
    }
}