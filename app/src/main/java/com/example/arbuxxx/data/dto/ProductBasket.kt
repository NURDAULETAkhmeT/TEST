package com.example.arbuxxx.data.dto

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity(tableName = "basket")
data class ProductBasket(
    @PrimaryKey(autoGenerate = false)
    val id:Int? = 0,
    val name: String,
    val money: Float,
    val description: String,
    val img: Int,
    val quantity:Int,
    val totalQuantity:Int = 0,
    val totalMoney:Float = 0F
)