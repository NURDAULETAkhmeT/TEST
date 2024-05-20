@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalGlideComposeApi::class,
    ExperimentalGlideComposeApi::class
)

package com.example.arbuxxx.presentation.Screen.Home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.arbuxxx.R
import com.example.arbuxxx.data.dto.Product
import com.example.arbuxxx.data.model.ProductViewModel


@Composable
fun Home(
    productViewModel: ProductViewModel
) {

    val productDataState by productViewModel.products.collectAsState()

    Column {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .fillMaxWidth()
                .height(132.dp)
                .wrapContentSize(
                    align = Alignment.BottomStart
                )
        ) {
            Text(
                text = "Фрукты",
                fontSize = 28.sp,
                color = colorResource(id = R.color.FF302C28),
                lineHeight = 33.41.sp,
                letterSpacing = 0.36.sp,
                modifier = Modifier
                    .padding(16.dp,7.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Количество столбцов
            contentPadding = PaddingValues(16.dp,10.dp),
            modifier = Modifier
                .padding(0.dp,8.dp)
        ) {
            if (productDataState.isNotEmpty()) {
                items(productDataState, key = { it.id!! }) { product ->
                    ProductItem(product, productViewModel)
                }
            } else {
                item {
                    Text(
                        text = "Нет данных для отображения",
                        modifier = Modifier
                            .padding(30.dp)
                    )
                }
            }
        }
    }


}

@Composable
fun ProductItem(
    product: Product,
    productViewModel: ProductViewModel
) {
    var totalQuantity by remember { mutableStateOf(product.totalQuantity) }

    Box(
        modifier = Modifier
            .padding(8.dp, 10.dp)
            .width(165.dp)
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {

                GlideImage(
                    model = product.img,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                color = colorResource(id = R.color.FF302C28),
                fontSize = 15.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.5.sp,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(1.dp))

            Text(
                text = "${product.money} kzt/кг",
                fontSize = 11.sp,
                lineHeight = 15.2.sp,
                color = Color.Gray,
                letterSpacing = 0.5.sp,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${product.quantity} кг",
                    fontSize = 12.sp,
                    lineHeight = 16.2.sp,
                    color = Color.Green,
                    letterSpacing = 0.5.sp
                )

                if (totalQuantity == 0) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(color = colorResource(id = R.color.FFB5A380))
                            .clickable {
                                totalQuantity = 1
                                productViewModel.addProduct(product)
                                productViewModel.addBasketsPlus(product)
                            }
                            .wrapContentSize(Alignment.Center)
                    ) {
                        GlideImage(
                            model = R.drawable.plus_no_active,
                            contentDescription = "",
                            modifier = Modifier.wrapContentSize(Alignment.Center)
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .size(100.dp, 32.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(colorResource(id = R.color.FFF3F1F0))
                            .wrapContentSize(Alignment.Center)
                    ) {
                        GlideImage(
                            model = R.drawable.minus_svgrepo_com,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable {
                                    if (totalQuantity > 0) {
                                        totalQuantity -= 1
                                        productViewModel.deleteProductMinus(product)
                                        productViewModel.deleteBasketsMinus(product)
                                    }
                                }
                        )
                        Text(
                            text = totalQuantity.toString(),
                            modifier = Modifier
                                .size(44.dp, 20.dp)
                                .wrapContentSize(Alignment.Center)
                        )

                        GlideImage(
                            model = R.drawable.plus_active,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable {
                                    if (totalQuantity < product.quantity) {
                                        totalQuantity += 1
                                        productViewModel.addProduct(product)
                                        productViewModel.addBasketsPlus(product)
                                    }
                                }
                        )
                    }
                }
            }
        }
    }
}

