package com.example.arbuxxx.presentation.model.BottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.arbuxxx.presentation.ui.theme.FF222222
import com.example.arbuxxx.presentation.ui.theme.FF4CAF50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation(
    navController: NavController,
    notificationCount: Int // Добавьте параметр для количества уведомлений
) {
    val listItems = listOf(
        ButtomItem.MenuHome,
        ButtomItem.MenuBasket,
        ButtomItem.MenuFavorite,
        ButtomItem.MenuProfile
    )
    NavigationBar(
        Modifier
            .fillMaxHeight(0.12f)
            .background(Color.White)
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    if (item.route == ButtomItem.MenuBasket.route && notificationCount > 0) {
                        BadgedBox(
                            badge = {
                                Badge { Text(notificationCount.toString()) }
                            }
                        ) {
                            Icon(painter = painterResource(id = item.iconId), contentDescription = item.route)
                        }
                    } else {
                        Icon(painter = painterResource(id = item.iconId), contentDescription = item.route)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = FF4CAF50,
                    unselectedIconColor = FF222222,
                    indicatorColor = Color.White
                )
            )
        }
    }
}
