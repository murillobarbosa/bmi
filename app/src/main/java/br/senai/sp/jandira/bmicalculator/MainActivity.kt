package br.senai.sp.jandira.bmicalculator

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.bmicalculator.calculate.calculate
import br.senai.sp.jandira.bmicalculator.calculate.getBmiClassification
import br.senai.sp.jandira.bmicalculator.model.Client
import br.senai.sp.jandira.bmicalculator.model.Product
import br.senai.sp.jandira.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            BMICalculatorTheme {
                CalculatorScreen()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CalculatorScreen() {

    var weightState by rememberSaveable {
        mutableStateOf("")
    }

    var heightState by rememberSaveable {
        mutableStateOf("")
    }

    var bmiState by rememberSaveable {
        mutableStateOf("0.0")
    }

    var bmiClassificationState by rememberSaveable {
        mutableStateOf("")
    }



    val context = LocalContext.current.applicationContext
    val context2 = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = br.senai.sp.jandira.bmicalculator.R.drawable.bmii),
                    contentDescription = "",
                    modifier = Modifier.size(120.dp)
                )
                Text(
                    text = stringResource(id = br.senai.sp.jandira.bmicalculator.R.string.title),
                    fontSize = 32.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp


                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)

            ) {
                Text(
                    text = stringResource(id = br.senai.sp.jandira.bmicalculator.R.string.weight_label),
                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp

                )
                OutlinedTextField(
                    value = weightState,
                    onValueChange = {
                        Log.i("ds2m", it)
                        weightState = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(
                    text = stringResource(id = br.senai.sp.jandira.bmicalculator.R.string.height_label),
                    modifier = Modifier.padding(top = 15.dp, bottom = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                OutlinedTextField(
                    value = heightState,
                    onValueChange = {
                        heightState = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Button(
                    onClick = {
                                var w = weightState.toDouble()
                                var h = heightState.toDouble()
                                var bmi = calculate(weight = w, height = h)
                                bmiState = String.format("%.1f", bmi)
                                bmiClassificationState = getBmiClassification(bmi , context)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                    // mudar cor do botão

                    border = BorderStroke(2.dp, Color.Black),
                    shape = RoundedCornerShape(20.dp),


                    ) {
                    Text(
                        text = stringResource(id = br.senai.sp.jandira.bmicalculator.R.string.button_calculate),
                        modifier = Modifier.padding(1.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp


                    )

                }
            }
            // FOOTER
            Column() {
                Card(
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = RoundedCornerShape(
                        topStart = 48.dp, topEnd = 48.dp
                    ), // formula para deixa o card na forma que você deseja

                    backgroundColor = Color.Black,   // Color(red = 79, green = 54, blue = 232)
                    border = BorderStroke(
                        0.5.dp,
                        Color.Black
                    ) // como fazer borda de qualquer elemento
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = br.senai.sp.jandira.bmicalculator.R.string.your_score),
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold


                        )
                        Text(
                            text = bmiState,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = bmiClassificationState,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,


                            )
                        Row() {          // para colocar os botões
                            Button(
                                onClick = {
                                    weightState = ""
                                    heightState = ""
                                    bmiClassificationState = ""
                                    bmiState = ""
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                            ) {
                                Text(text = stringResource(id = br.senai.sp.jandira.bmicalculator.R.string.reset))
                            }

                            Spacer(modifier = Modifier.width(15.dp))
                            Button(
                                onClick = {
                                          val openOther = Intent(context2, SignUpActivity::class.java)
                                          context2.startActivity(openOther)
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)

                            ) {
                                Text(text = stringResource(id = br.senai.sp.jandira.bmicalculator.R.string.share))
                            }
                        }
                    }

                }

            }
        }

    }
}
