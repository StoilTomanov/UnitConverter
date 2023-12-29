package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", fontSize = 20.sp, fontWeight = FontWeight(800))
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(value = "", onValueChange = {

        })
        Row {
            Box {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select", fontSize = 16.sp)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Button")
                }
                DropdownMenu(expanded = false, onDismissRequest = {}) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters", fontSize = 16.sp) },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters", fontSize = 16.sp) },
                        onClick = { /*TODO*/ }
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Box {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select", fontSize = 16.sp)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Button")
                }
                DropdownMenu(expanded = false, onDismissRequest = {}) {
                    DropdownMenuItem(
                        text = { Text(text = "Meters", fontSize = 16.sp) },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters", fontSize = 16.sp) },
                        onClick = { /*TODO*/ }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result:", fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}