@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.arbuxxx.presentation.Screen.Basket

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.arbuxxx.data.model.ProductViewModel
import com.example.arbuxxx.data.dto.ProductBasket

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Basket(productViewModel: ProductViewModel) {
    val productDataState by productViewModel.baskets.collectAsState()

    Column(
        modifier = Modifier
            .padding(8.dp, 35.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (productDataState.isNotEmpty()) {
            // Отображение списка продуктов
            productDataState.forEach { basket ->
                ProductItem(basket)
            }
        } else {
            // Отображение сообщения, если список пуст
            Text(
                text = "Нет данных для отображения"
            )
        }
    }

    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(
                Alignment.BottomCenter
            )
    ) {
        Text(text = "${productViewModel.totalAmount.value} KZT")
    }
}

@Composable
fun ProductItem(productBasket: ProductBasket) {
    ConstraintLayout(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
    ) {
        val(imageRef,nameRef,moneyRef,totalMoneyRef) = createRefs()

        GlideImage(
            model = productBasket.img,
            contentDescription = "",
            modifier = Modifier
                .size(95.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            contentScale = ContentScale.Crop
        )

        Text(
            text = "${productBasket.name} кг",
            modifier = Modifier
                .constrainAs(nameRef){
                    top.linkTo(parent.top)
                    start.linkTo(imageRef.end,4.dp)
                }
        )
        Text(
            text = "${productBasket.money} kzt/кг",
            color = Color.Gray,
            modifier = Modifier
                .constrainAs(moneyRef) {
                    top.linkTo(nameRef.bottom, 1.dp)
                    start.linkTo(imageRef.end, 4.dp)
                }
        )

        Text(
            text = "${productBasket.totalMoney} KZT",
            color = Color.Black,
            modifier = Modifier
                .constrainAs(totalMoneyRef) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )
    }
}