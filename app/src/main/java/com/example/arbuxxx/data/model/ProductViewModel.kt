package com.example.arbuxxx.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arbuxxx.data.dto.Product
import com.example.arbuxxx.R
import com.example.arbuxxx.data.repository.Repository
import com.example.arbuxxx.data.dto.ProductBasket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: Repository) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products


    private val _baskets = MutableStateFlow<List<ProductBasket>>(listOf())
    val baskets: StateFlow<List<ProductBasket>> get() = _baskets


    private val _totalAmount = MutableStateFlow(0f)
    val totalAmount: StateFlow<Float> get() = _totalAmount


    init {
        loadBaskets()
        loadProducts() // Загрузить продукты из базы данных при первой инициализации
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _products.value = repository.loadProducts()
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            repository.addItemProduct(product)
        }
    }

    fun deleteProductMinus(product: Product) {
        viewModelScope.launch {
            if (product.totalQuantity > 0) {
                repository.deleteItemProduct(product)
            }
        }
    }


    //Baskeet
    private fun loadBaskets() {
        viewModelScope.launch {
            _baskets.value = repository.loadBaskets()
            _totalAmount.value = repository.calculateTotalAmount(baskets.value)
        }
    }


    fun addBasketsPlus(product: Product) {
        viewModelScope.launch {
            repository.addItemBasketProduct(product)
            loadBaskets() // обновить список пользователей после добавления нового
        }
    }

    fun deleteBasketsMinus(product: Product) {
        viewModelScope.launch {
            repository.deleteItemBasketProduct(product)
            loadBaskets() // обновить список пользователей после добавления нового
        }
    }


}