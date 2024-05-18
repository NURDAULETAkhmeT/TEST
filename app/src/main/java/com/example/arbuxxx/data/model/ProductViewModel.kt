package com.example.arbuxxx.data.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arbuxxx.domain.model.Product
import com.example.arbuxxx.R
import com.example.arbuxxx.data.repository.ProductDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val productDao: ProductDao) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    init {
        loadProducts() // Загрузить продукты из базы данных при первой инициализации
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                val loadedProducts = productDao.getAllProducts()
                if (loadedProducts.isEmpty()) {
                    // Если база данных пуста, добавьте все элементы из списка в базу данных
                    productDao.insertAllProducts(DEFAULT_PRODUCTS)
                    _products.value = DEFAULT_PRODUCTS
                } else {
                    _products.value = loadedProducts
                }
                Log.d("ProductViewModel", "Loaded products: $loadedProducts")
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error loading products", e)
            }
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            productDao.addItemProduct(product)
        }
    }

    fun deleteProductMinus(product: Product) {
        viewModelScope.launch {
            if (product.totalQuantity > 0) {
                productDao.deleteItemProduct(product)
            }
        }
    }

    companion object {
        // Список продуктов по умолчанию
        private val DEFAULT_PRODUCTS = listOf(
            Product(
                id = 0,
                name = "Банан",
                money = 890F,
                description = "Сочные Африканские Бананы",
                img = R.drawable.product_banan,
                quantity = 27
            ),
            Product(
                id = 1,
                name = "Апельсин",
                money = 1430F,
                description = "Вкусные Апельсины",
                img = R.drawable.product_apelsin,
                quantity = 13
            ),
            Product(
                id = 2,
                name = "Яблоко",
                money = 1590F,
                description = "Местные Алматинские Яблоки",
                img = R.drawable.product_yabloko,
                quantity = 134
            ),
            Product(
                id = 3,
                name = "Клубника",
                money = 3625F,
                description = "Вкуснейшие клубники",
                img = R.drawable.product_klubnika,
                quantity = 17
            ),
            Product(
                id = 4,
                name = "Ананас",
                money = 1800F,
                description = "Хорошие Ананасы",
                img = R.drawable.product_ananas,
                quantity = 14
            ),
            Product(
                id = 5,
                name = "Манго",
                money = 1705F,
                description = "Из дального Вастока))",
                img = R.drawable.product_mango,
                quantity = 18
            ),
            Product(
                id = 6,
                name = "Какос",
                money = 2500F,
                description = "Из Ауыла Чунга Чанги",
                img = R.drawable.product_kakos,
                quantity = 21
            ),
            Product(
                id = 7,
                name = "Киви",
                money = 1435F,
                description = "Сладкие Киви",
                img = R.drawable.product_qivi,
                quantity = 3
            ),
        )
    }

}