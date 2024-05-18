package com.example.arbuxxx.presentation.Screen.Home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.arbuxxx.R
import com.example.arbuxxx.data.model.BasketProductViewModel
import com.example.arbuxxx.data.model.ProductViewModel
import com.example.arbuxxx.domain.model.Product
import com.example.arbuxxx.presentation.ui.theme.FF302C28
import com.example.arbuxxx.presentation.ui.theme.FF8BC34A
import com.example.arbuxxx.presentation.ui.theme.FFA1A5A1
import com.example.arbuxxx.presentation.ui.theme.FFB5A380
import com.example.arbuxxx.presentation.ui.theme.FFF3F1F0

@Preview
@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(
//    product: Product,
//    productViewModel: ProductViewModel,
//    basketProductViewModel: BasketProductViewModel
) {
    var totalQuantity by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .padding(8.dp,10.dp)
    ){
        ConstraintLayout(
            modifier = Modifier
                .size(165.dp,200.dp)
        ) {

            val(imageRef,nameRef,moneyRef,koliRef,plusRef,minusRef) = createRefs()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ){
                Image(painter = painterResource(id = R.drawable.product_apelsin),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize() // Заполняем все доступное пространство
                        .clip(shape = RectangleShape)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = "product.name",
                color = FF302C28,
                fontSize = 15.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.5.sp,
                modifier = Modifier
                    .constrainAs(nameRef){
                        top.linkTo(imageRef.bottom,8.dp)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = "product.money kzt/кг",
                fontSize = 11.sp,
                lineHeight = 15.2.sp,
                color = Color.Gray,
                letterSpacing = 0.5.sp,
                modifier = Modifier
                    .constrainAs(moneyRef){
                        top.linkTo(nameRef.bottom,1.dp)
                    }
            )

            Text(
                text = "product.quantity кг",
                fontSize = 12.sp,
                lineHeight = 16.2.sp,
                color = Color.Green,
                letterSpacing = 0.5.sp,
                modifier = Modifier
                    .constrainAs(koliRef){
                        top.linkTo(imageRef.bottom,10.dp)
                        end.linkTo(parent.end)
                    }
            )

            if (totalQuantity == 0) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(FFB5A380)
                        .clickable {
                            totalQuantity = 1
//                            productViewModel.addProduct(product)
//                            basketProductViewModel.addBasketsPlus(product)
                        }
                        .constrainAs(plusRef) {
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .wrapContentSize(Alignment.Center)
                ) {
                    Image(painter = painterResource(id = R.drawable.plus_no_active),
                        contentDescription = "",
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                    )
                }

            } else {

                Row(
                    modifier = Modifier
                        .size(100.dp, 32.dp)
                        .clip(shape = RectangleShape)
                        .clip(shape = RoundedCornerShape(100.dp))
                        .background(FFF3F1F0)
                        .clickable {
                            totalQuantity = 1
                        }
                        .constrainAs(minusRef) {
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .wrapContentSize(Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.minus_svgrepo_com),
                        contentDescription = "",
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                    )

                    Text(
                        text = "5",
                        Modifier
                            .size(44.dp,20.dp)
                            .wrapContentSize(Alignment.Center)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.plus_active),
                        contentDescription = "",
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                    )

                }
            }
        }
    }
}

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
