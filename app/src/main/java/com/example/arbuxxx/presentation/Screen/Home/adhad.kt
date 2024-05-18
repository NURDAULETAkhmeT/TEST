//package com.example.arbuxxx.presentation.Screen.Home
//
//package com.example.arbuxxx.presentation.Screen.Home
//
//import android.annotation.SuppressLint
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
//import com.bumptech.glide.integration.compose.GlideImage
//import com.example.arbuxxx.R
//import com.example.arbuxxx.domain.model.Product
//import com.example.arbuxxx.data.model.BasketProductViewModel
//import com.example.arbuxxx.data.model.ProductViewModel
//import com.example.arbuxxx.presentation.ui.theme.FF8BC34A
//import com.example.arbuxxx.presentation.ui.theme.FFA1A5A1
//
//@Composable
//fun Home(
//    productViewModel: ProductViewModel,
//    basketProductViewModel: BasketProductViewModel
//) {
//
//    val productDataState by productViewModel.products.collectAsState()
//
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2), // Количество столбцов
//        contentPadding = PaddingValues(16.dp,48.dp,16.dp,16.dp)
//    ) {
//        if (productDataState.isNotEmpty()) {
//            items(productDataState, key = { it.id!! }) { product ->
//                ProductItem(product, productViewModel, basketProductViewModel)
//            }
//        } else {
//            item {
//                Text(
//                    text = "Нет данных для отображения",
//                    modifier = Modifier
//                        .padding(30.dp)
//                )
//            }
//        }
//    }
//}
//
//@SuppressLint("StateFlowValueCalledInComposition")
//@OptIn(ExperimentalGlideComposeApi::class)
//@Composable
//fun ProductItem(
//    product: Product,
//    productViewModel: ProductViewModel,
//    basketProductViewModel: BasketProductViewModel
//) {
//    var totalQuantity by remember { mutableStateOf(product.totalQuantity) }
//
//    Column(
//        modifier = Modifier
//            .padding(8.dp, 45.dp)
//            .fillMaxWidth()
//    ) {
//        GlideImage(
//            model = imageUrl,
//            contentDescription = "image ${product.id}",
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(1f)
//                .clip(RoundedCornerShape(16.dp)),
//            contentScale = ContentScale.Crop
//        )
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(
//            text = name,
//            fontSize = 14.sp,
//            lineHeight = 16.2.sp,
//            letterSpacing = 0.5.sp
//        )
//        Text(
//            text = "${money} kzt/кг",
//            fontSize = 12.sp,
//            lineHeight = 16.2.sp,
//            color = Color.Gray,
//            letterSpacing = 0.5.sp
//        )
//        Text(
//            text = "* ${quality} кг",
//            fontSize = 12.sp,
//            lineHeight = 16.2.sp,
//            color = Color.Green,
//            letterSpacing = 0.5.sp
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//
//        if (totalQuantity == 0) {
//            AddToBasketButton(product, productViewModel, basketProductViewModel) {
//                totalQuantity = 1
//            }
//        } else {
//            UpdateBasketQuantityButtons(product, basketProductViewModel, productViewModel, totalQuantity) {
//                totalQuantity = it
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalGlideComposeApi::class)
//@Composable
//fun AddToBasketButton(
//    product: Product,
//    productViewModel: ProductViewModel,
//    basketProductViewModel: BasketProductViewModel,
//    onAdd: () -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .background(FFA1A5A1)
//            .fillMaxWidth()
//            .height(34.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .clickable {
//                productViewModel.addProduct(product)
//                basketProductViewModel.addBasketsPlus(product)
//                onAdd()
//            },
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = "${product.money} KZT",
//            fontSize = 14.sp,
//            lineHeight = 16.2.sp,
//            color = Color.Black,
//            letterSpacing = 0.5.sp,
//            modifier = Modifier.padding(start = 10.dp)
//        )
//        GlideImage(
//            model = R.drawable.plus_active,
//            contentDescription = "",
//            modifier = Modifier
//                .padding(end = 5.dp)
//                .size(24.dp)
//        )
//    }
//}
//
//@OptIn(ExperimentalGlideComposeApi::class)
//@Composable
//fun UpdateBasketQuantityButtons(
//    product: Product,
//    basketProductViewModel: BasketProductViewModel,
//    productViewModel: ProductViewModel,
//    totalQuality: Int,
//    onUpdate: (Int) -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .background(FF8BC34A)
//            .fillMaxWidth()
//            .height(34.dp)
//            .clip(RoundedCornerShape(16.dp)),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        GlideImage(
//            model = R.drawable.minus_svgrepo_com,
//            contentDescription = "",
//            modifier = Modifier
//                .padding(start = 5.dp)
//                .size(24.dp)
//                .clickable {
//                    productViewModel.deleteProductMinus(product)
//                    basketProductViewModel.deleteBasketsMinus(product)
//                    onUpdate(totalQuality - 1)
//                }
//        )
//        Text(
//            text = "$totalQuality",
//            fontSize = 14.sp,
//            lineHeight = 16.2.sp,
//            color = Color.Gray,
//            letterSpacing = 0.5.sp
//        )
//        GlideImage(
//            model = R.drawable.plus_no_active,
//            contentDescription = "",
//            modifier = Modifier
//                .padding(end = 5.dp)
//                .size(24.dp)
//                .clickable {
//                    productViewModel.addProduct(product)
//                    basketProductViewModel.addBasketsPlus(product)
//                    if (totalQuality < product.quantity) {
//                        onUpdate(totalQuality + 1)
//                    }
//                }
//        )
//    }
//}
