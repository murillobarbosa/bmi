package br.senai.sp.jandira.bmicalculator.calculate

import android.content.Context
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.savedstate.R
import kotlin.math.pow


fun calculate(weight: Double, height: Double) = weight / (height / 100).pow(2)

fun getBmiClassification(bmi: Double, context: Context): String{

    return if(bmi <= 18.5){
        context.getString(br.senai.sp.jandira.bmicalculator.R.string.underweight)
    } else if(bmi > 18.5 && bmi < 25.0){
        context.getString(br.senai.sp.jandira.bmicalculator.R.string.normal_weight)
    } else if(bmi >= 25.0 && bmi < 30.0){
        context.getString(br.senai.sp.jandira.bmicalculator.R.string.over_weight)
    } else if(bmi >= 30.0 && bmi < 40.0){
        context.getString(br.senai.sp.jandira.bmicalculator.R.string.obesity)


} else {
        context.getString(br.senai.sp.jandira.bmicalculator.R.string.morbid_obesity)
}
}

//fun calculate(weight: Double, height: Double): Double{
//    return weight / height.pow(2)
//}

// do jeito java
// public double calculate(double w, double h){
//  return w / (h * h);
// }