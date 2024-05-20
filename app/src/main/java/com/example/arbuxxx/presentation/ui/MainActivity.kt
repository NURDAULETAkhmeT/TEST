package com.example.arbuxxx.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.arbuxxx.data.dto.ProductDataBase
import com.example.arbuxxx.data.model.ProductViewModel
import com.example.arbuxxx.data.model.ProductsViewModelFactory
import com.example.arbuxxx.presentation.Screen.Home.Home
import com.example.arbuxxx.presentation.ui.theme.ARBUXXXTheme

class MainActivity : ComponentActivity() {
    private val databaseProduct by lazy { ProductDataBase.getDatabase(this) }
    private val ProductViewModel: ProductViewModel by viewModels {
        ProductsViewModelFactory(databaseProduct.repository())
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
                MainScreen(productViewModel = ProductViewModel)
            }
        }
    }
}
