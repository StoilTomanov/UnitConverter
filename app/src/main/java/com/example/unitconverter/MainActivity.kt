package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var isInputFieldAllowed by remember { mutableStateOf(true) }
    val convertFromUnitBtn = remember { mutableStateOf("Select") }
    val convertToUnitBtn = remember { mutableStateOf("Select") }
    val isOutputUnitExpanded = remember { mutableStateOf(false) }
    val isInputUnitExpanded = remember { mutableStateOf(false) }
    val inputConversionFactor = remember { mutableDoubleStateOf(1.00) }
    val outputConversionFactor = remember { mutableDoubleStateOf(1.00) }

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val convertedValue = inputValueDouble * inputConversionFactor.doubleValue * 100.0
        val result = (convertedValue / outputConversionFactor.doubleValue) / 100.0
        outputValue = result.toString()
    }

    fun resetIOValues() {
        inputValue = ""
        outputValue = ""
    }

    fun checkIsInputFieldAllowed() {
        if (convertFromUnitBtn.value == "Select" || convertToUnitBtn.value == "Select") {
            isInputFieldAllowed = false
            resetIOValues()
        } else {
            isInputFieldAllowed = true
        }
    }

    @Composable
    fun GetConvertUnitButtonComposable(
        isDropdownUnitExpanded: MutableState<Boolean>,
        convertUnitBtnText: MutableState<String>
    ) {
        return Button(
            modifier = Modifier.border(
                width = 4.dp,
                color = if (!isInputFieldAllowed) {
                    Color(244, 67, 54, 255)
                } else Color(14, 100, 142, 255),
                shape = RoundedCornerShape(25.dp)
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(14, 100, 142, 255)
            ),
            onClick = {
                checkIsInputFieldAllowed()
                isDropdownUnitExpanded.value = true
                convertUnits()
            }) {
            Text(text = convertUnitBtnText.value, fontSize = 16.sp)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Button")
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", fontSize = 20.sp, fontWeight = FontWeight(800))
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                if (convertFromUnitBtn.value == "Select" || convertToUnitBtn.value == "Select") {
                    isInputFieldAllowed = false
                    resetIOValues()
                } else {
                    if (it.toIntOrNull() != null || it.toDoubleOrNull() != null) {
                        isInputFieldAllowed = true
                        inputValue = it
                        convertUnits()
                    } else {
                        resetIOValues()
                    }
                }
            },
            label = { Text(text = "Enter value...") },
            trailingIcon = {
                Icon(Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier.clickable {
                        resetIOValues()
                    })
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Box {
                GetConvertUnitButtonComposable(
                    isDropdownUnitExpanded = isInputUnitExpanded,
                    convertUnitBtnText = convertFromUnitBtn
                )
                DropdownMenu(expanded = isInputUnitExpanded.value, onDismissRequest = {
                    isInputUnitExpanded.value = false
                }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters", fontSize = 16.sp) },
                        onClick = {
                            convertFromUnitBtn.value = "Centimeters"
                            isInputUnitExpanded.value = false
                            inputConversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters", fontSize = 16.sp) },
                        onClick = {
                            convertFromUnitBtn.value = "Meters"
                            isInputUnitExpanded.value = false
                            inputConversionFactor.doubleValue = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet", fontSize = 16.sp) },
                        onClick = {
                            convertFromUnitBtn.value = "Feet"
                            isInputUnitExpanded.value = false
                            inputConversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters", fontSize = 16.sp) },
                        onClick = {
                            convertFromUnitBtn.value = "Millimeters"
                            isInputUnitExpanded.value = false
                            inputConversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Box {
                GetConvertUnitButtonComposable(
                    isDropdownUnitExpanded = isOutputUnitExpanded,
                    convertUnitBtnText = convertToUnitBtn
                )
                DropdownMenu(expanded = isOutputUnitExpanded.value, onDismissRequest = {
                    isOutputUnitExpanded.value = false
                }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters", fontSize = 16.sp) },
                        onClick = {
                            convertToUnitBtn.value = "Centimeters"
                            isOutputUnitExpanded.value = false
                            inputConversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters", fontSize = 16.sp) },
                        onClick = {
                            convertToUnitBtn.value = "Meters"
                            isOutputUnitExpanded.value = false
                            inputConversionFactor.doubleValue = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet", fontSize = 16.sp) },
                        onClick = {
                            convertFromUnitBtn.value = "Feet"
                            isInputUnitExpanded.value = false
                            inputConversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters", fontSize = 16.sp) },
                        onClick = {
                            convertFromUnitBtn.value = "Millimeters"
                            isInputUnitExpanded.value = false
                            inputConversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputValue", fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}