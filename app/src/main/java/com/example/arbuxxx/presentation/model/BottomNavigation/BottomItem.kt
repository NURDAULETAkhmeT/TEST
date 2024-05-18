package com.example.arbuxxx.presentation.model.BottomNavigation

import com.example.arbuxxx.R


sealed class ButtomItem(val title: String, val iconId: Int, val route: String) {
    object MenuHome: ButtomItem("Главное", R.drawable.menu_home, "Home")
    object MenuBasket: ButtomItem("Поиск", R.drawable.menu_basket, "Basket")
    object MenuFavorite: ButtomItem("Избранное", R.drawable.menu_favorite, "Favorite")
    object MenuProfile: ButtomItem("Профиль", R.drawable.menu_profile, "Profile")
}