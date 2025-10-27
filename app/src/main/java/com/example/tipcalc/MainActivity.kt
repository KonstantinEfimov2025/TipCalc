package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalc.ui.theme.TipCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OrderScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun OrderScreen(modifier: Modifier = Modifier) {
    var orderAmount by remember { mutableStateOf("") }
    var dishCount by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf(0f) }

    val discountPercentage = calculateDiscount(dishCount.toIntOrNull() ?: 0)

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .padding(top = 50.dp)
            .padding(horizontal = 20.dp)
    ) {
        OrderAmountInput(
            orderAmount = orderAmount,
            onOrderAmountChange = { orderAmount = it }
        )

        DishCountInput(
            dishCount = dishCount,
            onDishCountChange = { dishCount = it }
        )

        TipSliderSection(
            tipPercentage = tipPercentage,
            onTipPercentageChange = { tipPercentage = it }
        )

        DiscountSection(discountPercentage = discountPercentage)
    }
}

@Composable
fun OrderAmountInput(
    orderAmount: String,
    onOrderAmountChange: (String) -> Unit
) {
    InputRow(
        label = "Сумма заказа:",
        value = orderAmount,
        onValueChange = onOrderAmountChange,
        boxWidth = 150.dp
    )
}

@Composable
fun DishCountInput(
    dishCount: String,
    onDishCountChange: (String) -> Unit
) {
    InputRow(
        label = "Количество блюд:",
        value = dishCount,
        onValueChange = onDishCountChange,
        boxWidth = 50.dp
    )
}

@Composable
fun InputRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    boxWidth: androidx.compose.ui.unit.Dp
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            fontSize = 15.sp,
            modifier = Modifier.padding(end = 8.dp)
        )
        Box(
            modifier = Modifier
                .width(boxWidth)
                .height(20.dp)
                .background(Color(0xFFFFC0CB))
                .padding(horizontal = 4.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            androidx.compose.foundation.text.BasicTextField(
                modifier = Modifier.width(boxWidth),
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun TipSliderSection(
    tipPercentage: Float,
    onTipPercentageChange: (Float) -> Unit
) {
    Column {
        Text(
            text = "Чаевые:",
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Slider(
            value = tipPercentage,
            onValueChange = onTipPercentageChange,
            valueRange = 0f..25f,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "0%")
            Text(text = "25%")
        }
    }
}

@Composable
fun DiscountSection(discountPercentage: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Скидка:",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
        DiscountRadioButtons(discountPercentage = discountPercentage)
    }
}

@Composable
fun DiscountRadioButtons(discountPercentage: Int) {
    Row {
        listOf(3, 5, 7, 10).forEach { discount ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = discountPercentage == discount,
                    onClick = {},
                    enabled = false
                )
                Text("${discount}%")
            }
        }
    }
}

private fun calculateDiscount(dishCount: Int): Int {
    return when (dishCount) {
        in 1..2 -> 3
        in 3..5 -> 5
        in 6..10 -> 7
        in 11..Int.MAX_VALUE -> 10
        else -> 0
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderScreenPreview() {
    TipCalcTheme {
        OrderScreen()
    }
}