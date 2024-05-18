package com.example.arbuxxx.data.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arbuxxx.data.repository.ProductBasketDao
import com.example.arbuxxx.data.repository.ProductDao
import com.example.arbuxxx.domain.model.Product
import com.example.arbuxxx.domain.model.ProductBasket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BasketProductViewModel(private val basketDao: ProductBasketDao) : ViewModel() {
    private val _baskets = MutableStateFlow<List<ProductBasket>>(listOf())
    val baskets: StateFlow<List<ProductBasket>> get() = _baskets

    init {
        loadBaskets()
    }

    private fun loadBaskets() {
        viewModelScope.launch {
            try {
                val loadedBaskets = basketDao.getAllBaskets()
                _baskets.value = loadedBaskets
                Log.d("ProductBasketViewModel", "Loaded products: $loadedBaskets")
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error loading products", e)
            }
        }
    }

    fun addBasketsPlus(product: Product) {
        viewModelScope.launch {
            basketDao.addItemBasketProduct(product)
            loadBaskets() // обновить список пользователей после добавления нового
        }
    }

    fun deleteBasketsMinus(product: Product) {
        viewModelScope.launch {
            if (product.totalQuantity > 0){
                basketDao.deleteItemBasketProduct(product)
            } else {
                basketDao.deleteListItemBasketProductById(product.id!!)
            }
            loadBaskets() // обновить список пользователей после добавления нового
        }
    }

    suspend fun autoDeleteBaskets(baskets: List<ProductBasket>){
        for (basket in baskets){
            if (basket.totalQuantity <= 0){
                basketDao.deleteListItemBasketProduct(basket)
            }
        }
    }
}