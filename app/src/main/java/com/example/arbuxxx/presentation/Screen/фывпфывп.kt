//package com.example.arbuxxx.presentation.Screen
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Text
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import com.bumptech.glide.integration.compose.GlideImage
//import com.example.arbuxxx.R
//import com.example.arbuxxx.presentation.ui.theme.FF8BC34A
//
//Box(
//modifier = Modifier
//.size(130.dp, 34.dp)
//.clip(shape = RectangleShape)
//.clip(shape = RoundedCornerShape(16.dp))
//.constrainAs(plusRef) {
//    top.linkTo(priseRef.bottom, 25.dp)
//},){
//    Row(
//        modifier = Modifier
//            .background(FF8BC34A)
//            .fillMaxSize()
//    ) {
//        ConstraintLayout(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            var (textRef, imageRef,imageMinusREf) = createRefs()
//
//            GlideImage(
//                model = R.drawable.minus_svgrepo_com,
//                contentDescription = "",
//                modifier = Modifier
//                    .constrainAs(imageMinusREf) {
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
//                        start.linkTo(parent.end, 5.dp)
//                    }
//                    .clickable {
//                        basketProductViewModel.deleteBasketsMinus(product)
//                    }
//            )
//
//            Text(
//                text = "${product.totalQuantity} ",
//                fontSize = 14.sp,
//                lineHeight = 16.2.sp,
//                color = Color.Gray,
//                letterSpacing = 0.5.sp,
//                modifier = Modifier
//                    .constrainAs(textRef) {
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
//                        start.linkTo(imageMinusREf.end)
//                        end.linkTo(imageRef.start)
//                    }
//            )
//            GlideImage(
//                model = R.drawable.plus_no_active,
//                contentDescription = "",
//                modifier = Modifier
//                    .constrainAs(imageRef) {
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
//                        end.linkTo(parent.end, 5.dp)
//                    }
//                    .clickable {
//                        basketProductViewModel.addBasketsPlus(product)
//                    }
//            )
//        }
//    }
//}