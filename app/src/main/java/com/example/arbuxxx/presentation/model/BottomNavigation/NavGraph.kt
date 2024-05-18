package com.example.arbuxxx.presentation.model.BottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.arbuxxx.data.model.BasketProductViewModel
import com.example.arbuxxx.data.model.ProductViewModel
import com.example.arbuxxx.presentation.Screen.Basket.Basket
import com.example.arbuxxx.presentation.Screen.Favorite.Favorite
import com.example.arbuxxx.presentation.Screen.Home.Home
import com.example.arbuxxx.presentation.Screen.Profile.Profile
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavGraph(
    navHostController: NavHostController,
    productViewModel: ProductViewModel,
    basketProductViewModel: BasketProductViewModel
) {

    NavHost(navController = navHostController, startDestination = "Home") {
        composable("Home") {
            Home(productViewModel,basketProductViewModel)
        }

        composable("Basket") {
            Basket(basketProductViewModel)
        }

        composable("Favorite") {
            Favorite()
        }

        composable("Profile") {
            Profile()
        }
    }
}