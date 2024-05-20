package com.example.arbuxxx.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.arbuxxx.data.model.ProductViewModel
import com.example.arbuxxx.presentation.model.BottomNavigation.BottomNavigation
import com.example.arbuxxx.presentation.model.BottomNavigation.NavGraph

// Состояние для управления видимостью нижней панели навигации
val bottomNavigationVisible: MutableState<Boolean> = mutableStateOf(true)

fun hideBottomNavigationBar(visible: Boolean) {
    // Устанавливаем новое состояние для видимости нижней панели навигации
    bottomNavigationVisible.value = visible
}

@Composable
fun MainScreen(productViewModel: ProductViewModel) {
    val navController = rememberNavController()
    val notificationCount = productViewModel.baskets.collectAsState().value.size

    Column(Modifier.fillMaxSize()) {
        key(bottomNavigationVisible.value){
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                NavGraph(navController, productViewModel)
            }
            if (bottomNavigationVisible.value) {
                BottomNavigation(
                    navController = navController,
                    notificationCount
                )
            }
        }
    }
}
