package com.example.fitpath

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.fitpath.databinding.FragmentCaloriCalculatorBinding


class CaloriCalculatorFragment : Fragment() {
    private lateinit var design:FragmentCaloriCalculatorBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentCaloriCalculatorBinding.inflate(inflater,container,false)



        design.buttonCalculate.setOnClickListener {
            val age:String = design.textAge.text.toString()
            val height:String = design.textHeight.text.toString()
            val weight:String = design.textWeight.text.toString()

            val splitheightArray = height.split(".")
            val splitheight = splitheightArray[0]+splitheightArray[1]

            val BMIResult = (weight.toDouble()/(height.toDouble()*height.toDouble())).toInt()

            if (BMIResult.toDouble()<16){
                design.textViewBMIResult.text = "Status : Severe thinness"
            }else if (BMIResult.toDouble()<17){
                design.textViewBMIResult.text = "Status : Moderate thinness"
            }else if (BMIResult.toDouble()<18.4){
                design.textViewBMIResult.text = "Status : Mild thinness"
            }else if (BMIResult.toDouble()<24.9){
                design.textViewBMIResult.text = "Status : Normal range"
            }else if (BMIResult.toDouble()<29.9){
                design.textViewBMIResult.text = "Status : Overweight (Pre-obese)"
            }else if (BMIResult.toDouble()<34.9){
                design.textViewBMIResult.text = "Status : Obese (Class I)"
            }else if (BMIResult.toDouble()<39.9){
                design.textViewBMIResult.text = "Status : Obese (Class II)"
            }else if (BMIResult.toDouble()>=40){
                design.textViewBMIResult.text = "Status : Obese (Class III)"
            }

            design.textViewBMI.text = "BMI : ${BMIResult.toFloat()}"

            val manBMR  = 66.5+(13.75*weight.toDouble()) + (5*splitheight.toInt()) - (6.77*age.toDouble())
            val womanBMR  = 655.1+(9.56*weight.toDouble()) + (1.85*splitheight.toInt()) - (4.67*age.toDouble())

            val manRadioButtonStatus = design.radioButtonMan.isChecked
            val womanRadioButtonStatus = design.radioButtonWoman.isChecked

            val activityOne = design.radioButtonActivityOne.isChecked
            val activityTwo = design.radioButtonActivityTwo.isChecked
            val activityThree = design.radioButtonActivityThree.isChecked
            val activityFour = design.radioButtonActivityFour.isChecked

            if (manRadioButtonStatus){
                if (activityOne){
                    val calori = manBMR*1.2
                    design.textViewDailyCalori.text = "Daily Calori : ${calori}"
                }
                if (activityTwo){
                    val calori = manBMR*1.3
                    design.textViewDailyCalori.text = "Daily Calori : ${calori}"
                }
                if (activityThree){
                    val calori = manBMR*1.4
                    design.textViewDailyCalori.text = "Daily Calori : ${calori}"
                }
                if (activityFour){
                    val calori = manBMR*1.5
                    design.textViewDailyCalori.text = "Daily Calori : ${calori}"
                }
            }
            if (womanRadioButtonStatus){
                if (activityOne){
                    val calori = womanBMR*1.2
                    design.textViewDailyCalori.text = "Daily Calori : ${calori}"
                }
                if (activityTwo){
                    val calori = womanBMR*1.3
                    design.textViewDailyCalori.text = "Daily Calori : ${calori}"
                }
                if (activityThree){
                    val calori = womanBMR*1.4
                    design.textViewDailyCalori.text = "Daily Calori : ${calori}"
                }
                if (activityFour){
                    val calori = womanBMR*1.5
                    design.textViewDailyCalori.text = "Daily Calori : ${calori}"
                }
            }
        }

        return design.root
    }


}