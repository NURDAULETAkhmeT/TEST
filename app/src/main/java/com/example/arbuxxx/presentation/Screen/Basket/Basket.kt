package com.example.arbuxxx.presentation.Screen.Basket

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.arbuxxx.data.model.BasketProductViewModel
import com.example.arbuxxx.domain.model.Product
import com.example.arbuxxx.domain.model.ProductBasket

@Composable
fun Basket(basketProductViewModel: BasketProductViewModel) {
    val productDataState by basketProductViewModel.baskets.collectAsState()

    // Создаем переменную для хранения общей суммы продуктов
    var totalAmount by remember { mutableStateOf(0F) }

    // Вычисляем общую сумму продуктов при каждом изменении состояния корзины
    LaunchedEffect(productDataState) {
        totalAmount = productDataState.sumBy { it.totalMoney.toInt() }.toFloat()
    }

    Column(
        modifier = Modifier
            .padding(8.dp, 35.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (productDataState.isNotEmpty()) {
            // Отображение списка продуктов
            productDataState.forEach { basket ->
                ProductItem(basket,basketProductViewModel)
            }
        } else {
            // Отображение сообщения, если список пуст
            Text(
                text = "Нет данных для отображения"
            )
        }
    }

    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(
                Alignment.BottomCenter
            )
    ) {
        Text(text = "${totalAmount} KZT")
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(productBasket: ProductBasket,basketProductViewModel: BasketProductViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
    ) {
        val(imageRef,nameRef,moneyRef,PlusRef,XRef,totalMoneyRef) = createRefs()

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