package com.example.arbuxxx.data.repository

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.arbuxxx.R
import com.example.arbuxxx.data.dto.Product
import com.example.arbuxxx.data.dto.ProductBasket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Dao
interface Repository {

//PrdouctDao

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

    suspend fun loadProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val loadedProducts = getAllProducts()
                if (loadedProducts.isEmpty()) {
                    // Если база данных пуста, добавьте все элементы из списка в базу данных
                    insertAllProducts(DEFAULT_PRODUCTS)
                    DEFAULT_PRODUCTS // Возвращаем список по умолчанию
                } else {
                    loadedProducts // Возвращаем загруженные продукты
                }
            } catch (e: Exception) {
                // Обработка ошибок при загрузке продуктов
                Log.e("RepositoryImpl", "Error loading products", e)
                emptyList() // Возвращаем пустой список в случае ошибки
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


//BasketDao

    suspend fun loadBaskets(): List<ProductBasket> {
        return try {
            val loadedBaskets = getAllBaskets()
            Log.d("ProductBasketViewModel", "Loaded products: $loadedBaskets")
            loadedBaskets // Возвращаем загруженные корзины
        } catch (e: Exception) {
            Log.e("ProductViewModel", "Error loading products", e)
            emptyList() // Возвращаем пустой список в случае ошибки
        }
    }

    suspend fun calculateTotalAmount(baskets: List<ProductBasket>):Float {
        val total = baskets.sumBy { it.totalMoney.toInt() }
        val _totalAmount = total.toFloat()

        return _totalAmount
    }

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
        if (product.totalQuantity > 0) {
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
        } else {
            deleteListItemBasketProductById(product.id!!)
        }
    }

}