package com.example.arbuxxx.data.dto

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.arbuxxx.data.repository.Repository

@Database(
    entities = [Product::class, ProductBasket::class],
    version = 1
)

abstract class ProductDataBase: RoomDatabase() {

    abstract fun repository(): Repository


    companion object {
        @Volatile
        private var INSTANCE: ProductDataBase? = null

        fun getDatabase(context: Context): ProductDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}