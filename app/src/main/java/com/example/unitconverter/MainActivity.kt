package com.example.unitconverter

import android.content.Context
import android.os.Build
import android.os.Bundle    //used
import android.widget.Toast
import androidx.activity.ComponentActivity  //used
import androidx.activity.compose.setContent //used
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column //used
import androidx.compose.foundation.layout.Row //used
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize //used
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button //used
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme //used
import androidx.compose.material3.OutlinedTextField //used
import androidx.compose.material3.Surface //used
import androidx.compose.material3.Text //used
import androidx.compose.runtime.Composable      // allows us to use Composable keyword to create composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier //used
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview //used
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme //used

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//
                    UnitConverter()

                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UnitConverter()
{
    val context = LocalContext.current
    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Meters(m)") }
    var outputUnit by remember{ mutableStateOf("Meters(m)") }
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false) }
    val iconversionFactor = remember { mutableStateOf(1.00) }
    val oconversionFactor = remember { mutableStateOf(1.00) }


    val appNameTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 40.sp,
        color = Color.Red


    )
    fun convertUnits()
    {
        // ?: = elvis operator - if result is null use default value i.e 0.0
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0

        if(inputValueDouble == 0.0)
        {
            Toast.makeText(context, "Please enter valid number", Toast.LENGTH_SHORT).show()
        }
        val result = (inputValueDouble * iconversionFactor.value / oconversionFactor.value)
        val roundedResult = String.format("%.3f", result)
        outputValue = roundedResult
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    // here all lines are stacked bellow one another
        Text("Unit Converter", modifier = Modifier.padding(10.dp), style = appNameTextStyle)
        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it},
            label = { Text(text = "Enter Value")}
            //  this {} is anonymous fun() no name but can execute
            // here we tell what will happen if value in TextField changed.
            )

        Row {
            // here like rows, next to each other
                Box{
                    // INPUT BUTTON
                    Button(onClick = { iExpanded = true }) {
                        Text(text = inputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Shows list of items")
                    }
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeters(cm)") },
                            onClick = {
                                inputUnit = "cm"
                                iExpanded = false
                                iconversionFactor.value = 0.01
                                convertUnits()
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Meters(m)") },
                            onClick = {
                                inputUnit = "m"
                                iExpanded = false
                                iconversionFactor.value = 1.0
                                convertUnits()
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Feet(ft)") },
                            onClick = {
                                inputUnit = "ft"
                                iExpanded = false
                                iconversionFactor.value = 0.30848    // 1 foot = 0.30848
                                convertUnits()
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Millimeters(mm)") },
                            onClick = {
                                inputUnit = "mm"
                                iExpanded = false
                                iconversionFactor.value = 0.001
                                convertUnits()
                            }
                        )
                    }
                 }
            Spacer(modifier = Modifier.width(16.dp))

                Box{
                    // OUTPUT BUTTON
                    Button(onClick = { oExpanded = true}) {
                        Text(text= outputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Shows list of items")
                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeters(cm)") },
                            onClick = {
                                outputUnit = "cm"
                                oExpanded = false
                                oconversionFactor.value = 0.01
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Meters(m)") },
                            onClick = {
                                outputUnit = "m"
                                oExpanded = false
                                oconversionFactor.value = 1.0
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Feet(ft)") },
                            onClick = {
                                outputUnit = "ft"
                                oExpanded = false
                                oconversionFactor.value = 0.30848    // 1 foot = 0.30848
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Millimeters(mm)") },
                            onClick = {
                                outputUnit = "mm"
                                oExpanded = false
                                oconversionFactor.value = 0.001
                                convertUnits()
                            }
                        )
                    }

                }

            }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ${outputValue} $outputUnit",
            style = MaterialTheme.typography.headlineSmall
            )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}