package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
        setContent {
            TipCalcTheme {
                TipCalculatorApp()
            }
        }
    }
}

@Composable
fun TipCalculatorApp() {
    var orderAmount by remember { mutableStateOf("") }
    var dishCount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Поле ввода суммы заказа
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Сумма заказа:",
                fontSize = 15.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(20.dp)
                    .background(Color(0xFFFFC0CB))
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = orderAmount,
                    onValueChange = { orderAmount = it },
                    textStyle = TextStyle(fontSize = 14.sp, color = Color.Black)
                )
            }
        }

        // Поле ввода количества блюд
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Количество блюд:",
                fontSize = 15.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(20.dp)
                    .background(Color(0xFFFFC0CB))
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = dishCount,
                    onValueChange = { dishCount = it },
                    textStyle = TextStyle(fontSize = 14.sp, color = Color.Black)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview() {
    TipCalcTheme {
        TipCalculatorApp()
    }
}