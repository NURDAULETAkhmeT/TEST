package com.example.arbuxxx.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.arbuxxx.data.dto.ProductBasketDataBase
import com.example.arbuxxx.domain.model.Product
import com.example.arbuxxx.data.dto.ProductDataBase
import com.example.arbuxxx.data.model.BasketProductViewModel
import com.example.arbuxxx.data.model.BasketProductsViewModelFactory
import com.example.arbuxxx.data.model.ProductViewModel
import com.example.arbuxxx.data.model.ProductsViewModelFactory
import com.example.arbuxxx.presentation.Screen.Home.Home
import com.example.arbuxxx.presentation.ui.theme.ARBUXXXTheme

class MainActivity : ComponentActivity() {
    private val databaseProduct by lazy { ProductDataBase.getDatabase(this) }
    private val ProductViewModel: ProductViewModel by viewModels {
        ProductsViewModelFactory(databaseProduct.productDao())
    }

    private val databaseBasket by lazy { ProductBasketDataBase.getBasketDatabase(this) }
    private val basketViewModel: BasketProductViewModel by viewModels {
        BasketProductsViewModelFactory(databaseBasket.productBasketDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )

        setContent {
            ARBUXXXTheme {
                MainScreen(productViewModel = ProductViewModel,basketViewModel)
            }
        }
    }
}
