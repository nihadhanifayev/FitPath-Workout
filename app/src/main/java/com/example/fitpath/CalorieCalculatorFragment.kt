package com.example.fitpath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.fitpath.databinding.FragmentCaloriCalculatorBinding
import com.example.fitpath.models.CalorieCalculatorFragmentViewModel


class CalorieCalculatorFragment : Fragment() {
    private lateinit var design:FragmentCaloriCalculatorBinding
    private lateinit var viewmodel:CalorieCalculatorFragmentViewModel
    private var manRadioButtonStatus = false
    private var womanRadioButtonStatus = false
    private var activityOne = false
    private var activityTwo = false
    private var activityThree = false
    private var activityFour = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_calori_calculator,container,false)
        design.caloriecalculatorfragmentobject = this

        viewmodel.BMIResultLiveData.observe(viewLifecycleOwner) { bmiresult ->
            design.bmiResult = bmiresult
        }
        viewmodel.DailyCalorieLiveData.observe(viewLifecycleOwner) { dailycalorie ->
            design.dailyCalorie = dailycalorie
        }
        viewmodel.BMI.observe(viewLifecycleOwner) { bmi ->
            design.bmi = bmi
        }
        return design.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:CalorieCalculatorFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }
    fun calculateButton(age:String,height:String,weight:String){
        manRadioButtonStatus = design.radioButtonMan.isChecked
        womanRadioButtonStatus = design.radioButtonWoman.isChecked
        activityOne = design.radioButtonActivityOne.isChecked
        activityTwo = design.radioButtonActivityTwo.isChecked
        activityThree = design.radioButtonActivityThree.isChecked
        activityFour = design.radioButtonActivityFour.isChecked
        if (manRadioButtonStatus){
            if (activityOne){
                viewmodel.calorieCalculate(age,height,weight,"man","one")
            }
            if (activityTwo){
                viewmodel.calorieCalculate(age,height,weight,"man","two")
            }
            if (activityThree){
                viewmodel.calorieCalculate(age,height,weight,"man","three")
            }
            if (activityFour){
                viewmodel.calorieCalculate(age,height,weight,"man","four")
            }
        }
        if (womanRadioButtonStatus){
            if (activityOne){
                viewmodel.calorieCalculate(age,height,weight,"woman","one")
            }
            if (activityTwo){
                viewmodel.calorieCalculate(age,height,weight,"woman","two")
            }
            if (activityThree){
                viewmodel.calorieCalculate(age,height,weight,"woman","three")
            }
            if (activityFour){
                viewmodel.calorieCalculate(age,height,weight,"woman","four")
            }
        }
    }


}