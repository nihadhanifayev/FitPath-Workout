package com.example.fitpath.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpath.data.model.Activity
import com.example.fitpath.data.model.Gender

class CalorieCalculatorFragmentViewModel:ViewModel() {
    var bmiResultLiveData = MutableLiveData<String>()
    var dailyCalorieLiveData = MutableLiveData<String>()
    var bmi = MutableLiveData<Int>()

    init {
        bmiResultLiveData = MutableLiveData<String>()
        dailyCalorieLiveData = MutableLiveData<String>()
        bmi = MutableLiveData<Int>()
    }
    var bmiResult = 0
    var splitHeight = ""
    var selectedGender: Gender? = null
    var selectedActivity: Activity? = null

    fun calorieCalculateClickCalculateButton(age:String,height:String,weight:String){
        val gender = selectedGender ?: return
        val activity = selectedActivity ?: return
        val splitHeightArray = height.split(".")
        splitHeight = splitHeightArray[0]+splitHeightArray[1]
        calculateBmi(height,weight)
        val bmr = calculateBmr(age.toInt(),weight.toDouble(),gender)
        dailyCalorieLiveData.value = calculateDailyCalorie(bmr,activity).toString()
        calculateBmiResult()
    }

    private fun calculateBmiResult(){
        if (bmiResult.toDouble()<16){
            bmiResultLiveData.value = "Severe thinness"
        }else if (bmiResult.toDouble()<17){
            bmiResultLiveData.value = "Moderate thinness"
        }else if (bmiResult.toDouble()<18.4){
            bmiResultLiveData.value = "Mild thinness"
        }else if (bmiResult.toDouble()<24.9){
            bmiResultLiveData.value = "Normal range"
        }else if (bmiResult.toDouble()<29.9){
            bmiResultLiveData.value = "Overweight (Pre-obese)"
        }else if (bmiResult.toDouble()<34.9){
            bmiResultLiveData.value = "Obese (Class I)"
        }else if (bmiResult.toDouble()<39.9){
            bmiResultLiveData.value = "Obese (Class II)"
        }else if (bmiResult.toDouble()>=40){
            bmiResultLiveData.value = "Obese (Class III)"
        }
    }
    private fun calculateBmi(height:String, weight:String):Int{
        bmiResult = (weight.toDouble()/(height.toDouble()*height.toDouble())).toInt()
        bmi.value = bmiResult
        return bmiResult

    }
    private fun calculateBmr(age: Int, weight:Double, gender: Gender):Double{
        return if(gender == Gender.MAN){
            66.5+(13.75*weight) + (5*splitHeight.toInt()) - (6.77*age.toDouble())
        }else{
            655.1+(9.56*weight) + (1.85*splitHeight.toInt()) - (4.67*age.toDouble())
        }
    }
    private fun calculateDailyCalorie(bmr: Double, activityLevel: Activity): Double {
        val multiplier = when (activityLevel) {
            Activity.ONE -> 1.2
            Activity.TWO -> 1.3
            Activity.THREE -> 1.4
            Activity.FOUR -> 1.5
        }
        return bmr * multiplier
    }
}