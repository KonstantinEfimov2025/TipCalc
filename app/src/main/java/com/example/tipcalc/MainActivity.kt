package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        Text(text = "Сумма заказа: $orderAmount")
        Text(text = "Количество блюд: $dishCount")
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview() {
    TipCalcTheme {
        TipCalculatorApp()
    }
}