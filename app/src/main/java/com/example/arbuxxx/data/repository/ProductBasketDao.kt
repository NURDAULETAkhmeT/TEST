package com.example.arbuxxx.data.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.arbuxxx.domain.model.Product
import com.example.arbuxxx.domain.model.ProductBasket

@Dao
interface ProductBasketDao  {

        @Delete
        suspend fun deleteListItemBasketProduct(productBasket: ProductBasket)

        @Query("DELETE FROM basket WHERE id = :basketId")
        suspend fun deleteListItemBasketProductById(basketId: Int)

        @Query("SELECT * FROM basket")
        suspend fun getAllBaskets(): List<ProductBasket>

        @Upsert
        suspend fun insertProductBasket(productBasket: ProductBasket)

        suspend fun addItemBasketProduct(product: Product) {
                product?.let {
                        if (it.totalQuantity <= it.quantity) {
                                val productBasket = ProductBasket(
                                        id = it.id,
                                        name = it.name,
                                        money = it.money,
                                        description = it.description,
                                        img = it.img,
                                        quantity = it.quantity,
                                        totalQuantity = it.totalQuantity,
                                        totalMoney = it.totalQuantity * it.money
                                )
                                insertProductBasket(productBasket)
                        }
                }

        }

        suspend fun deleteItemBasketProduct(product: Product) {
                product?.let {
                                val productBasket = ProductBasket(
                                        id = it.id,
                                        name = it.name,
                                        money = it.money,
                                        description = it.description,
                                        img = it.img,
                                        quantity = it.quantity,
                                        totalQuantity = it.totalQuantity,
                                        totalMoney = it.totalQuantity * it.money
                                )
                                insertProductBasket(productBasket)
                }
        }
}