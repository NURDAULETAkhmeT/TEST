package com.example.arbuxxx.data.dto

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.arbuxxx.data.repository.ProductBasketDao
import com.example.arbuxxx.domain.model.ProductBasket

@Database(
    entities = [ProductBasket::class],
    version = 1
)

abstract class ProductBasketDataBase: RoomDatabase() {

    abstract fun productBasketDao(): ProductBasketDao

    companion object {
        @Volatile
        private var INSTANCE: ProductBasketDataBase? = null

        fun getBasketDatabase(context: Context): ProductBasketDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductBasketDataBase::class.java,
                    "basket_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}