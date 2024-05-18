package com.example.arbuxxx.domain.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id:Int? = 0,
    val name: String,
    val money: Float,
    val description: String,
    val img: Int,
    val quantity: Int,
    var totalQuantity:Int = 0,
    val totalMoney:Float = 0F
)
