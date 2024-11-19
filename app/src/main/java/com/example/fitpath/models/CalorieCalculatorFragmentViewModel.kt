package com.example.fitpath.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalorieCalculatorFragmentViewModel:ViewModel() {
    var BMIResultLiveData = MutableLiveData<String>()
    var DailyCalorieLiveData = MutableLiveData<String>()
    var BMI = MutableLiveData<Int>()

    init {
        BMIResultLiveData = MutableLiveData<String>()
        DailyCalorieLiveData = MutableLiveData<String>()
        BMI = MutableLiveData<Int>()
    }
    fun calorieCalculate(age:String,height:String,weight:String,gender:String,activity:String){

        val splitheightArray = height.split(".")
        val splitheight = splitheightArray[0]+splitheightArray[1]

        val BMIResult = (weight.toDouble()/(height.toDouble()*height.toDouble())).toInt()
        BMI.value = BMIResult

        if (BMIResult.toDouble()<16){
            BMIResultLiveData.value = "Severe thinness"
        }else if (BMIResult.toDouble()<17){
            BMIResultLiveData.value = "Moderate thinness"
        }else if (BMIResult.toDouble()<18.4){
            BMIResultLiveData.value = "Mild thinness"
        }else if (BMIResult.toDouble()<24.9){
            BMIResultLiveData.value = "Normal range"
        }else if (BMIResult.toDouble()<29.9){
            BMIResultLiveData.value = "Overweight (Pre-obese)"
        }else if (BMIResult.toDouble()<34.9){
            BMIResultLiveData.value = "Obese (Class I)"
        }else if (BMIResult.toDouble()<39.9){
            BMIResultLiveData.value = "Obese (Class II)"
        }else if (BMIResult.toDouble()>=40){
            BMIResultLiveData.value = "Obese (Class III)"
        }
        val manBMR  = 66.5+(13.75*weight.toDouble()) + (5*splitheight.toInt()) - (6.77*age.toDouble())
        val womanBMR  = 655.1+(9.56*weight.toDouble()) + (1.85*splitheight.toInt()) - (4.67*age.toDouble())

        if (gender=="man"){
            if (activity == "one"){
                val calorie = manBMR*1.2
                DailyCalorieLiveData.value = calorie.toString()
            }
            if (activity == "two"){
                val calorie = manBMR*1.3
                DailyCalorieLiveData.value = calorie.toString()
            }
            if (activity == "three"){
                val calorie = manBMR*1.4
                DailyCalorieLiveData.value = calorie.toString()
            }
            if (activity == "four"){
                val calorie = manBMR*1.5
                DailyCalorieLiveData.value = calorie.toString()
            }
        }
        if (gender == "woman"){
            if (activity == "one"){
                val calorie = womanBMR*1.2
                DailyCalorieLiveData.value = calorie.toString()
            }
            if (activity == "two"){
                val calorie = womanBMR*1.3
                DailyCalorieLiveData.value = calorie.toString()
            }
            if (activity == "three"){
                val calorie = womanBMR*1.4
                DailyCalorieLiveData.value = calorie.toString()
            }
            if (activity == "four"){
                val calorie = womanBMR*1.5
                DailyCalorieLiveData.value = calorie.toString()
            }
        }
    }
}