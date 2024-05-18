package com.example.arbuxxx.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.arbuxxx.data.repository.ProductBasketDao

class BasketProductsViewModelFactory(private val basketDao: ProductBasketDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BasketProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BasketProductViewModel(basketDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}