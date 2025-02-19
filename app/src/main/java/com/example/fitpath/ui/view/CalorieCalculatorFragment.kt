package com.example.fitpath.ui.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.fitpath.R
import com.example.fitpath.data.model.Activity
import com.example.fitpath.data.model.Gender
import com.example.fitpath.databinding.FragmentCaloriCalculatorBinding
import com.example.fitpath.ui.viewmodels.CalorieCalculatorFragmentViewModel


class CalorieCalculatorFragment : Fragment() {
    private lateinit var design:FragmentCaloriCalculatorBinding
    private val viewmodel: CalorieCalculatorFragmentViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,
            R.layout.fragment_calori_calculator,container,false)
        design.caloriecalculatorfragmentobject = this
        observeLiveData()
        design.cardViewResult.visibility = View.GONE
        return design.root
    }
    fun calculateButton(age:String,height:String,weight:String){
        radioButtonCheckGender()
        radioButtonCheckActivity()
        viewmodel.calorieCalculateClickCalculateButton(age,height,weight)
        animation()
    }
    private fun observeLiveData(){
        viewmodel.bmiResultLiveData.observe(viewLifecycleOwner) { bmiResult ->
            design.bmiResult = bmiResult
        }
        viewmodel.dailyCalorieLiveData.observe(viewLifecycleOwner) { dailyCalorie ->
            design.dailyCalorie = dailyCalorie
        }
        viewmodel.bmi.observe(viewLifecycleOwner) { bmi ->
            design.bmi = bmi
        }
    }
    private fun radioButtonCheckGender(){
        return if (design.radioButtonMan.isChecked){
            viewmodel.selectedGender = Gender.MAN
        }else{
            viewmodel.selectedGender = Gender.WOMAN
        }
    }
    private fun radioButtonCheckActivity(){
        return if (design.radioButtonActivityOne.isChecked){
            viewmodel.selectedActivity = Activity.ONE
        }else if(design.radioButtonActivityTwo.isChecked){
            viewmodel.selectedActivity = Activity.TWO
        } else  if (design.radioButtonActivityThree.isChecked){
            viewmodel.selectedActivity = Activity.THREE
        } else {
            viewmodel.selectedActivity = Activity.FOUR
        }
    }
    private fun animation(){

        design.cardViewResult.visibility = View.VISIBLE

        val scaleX = ObjectAnimator.ofFloat(design.cardViewResult, View.SCALE_X, 1f, 1.5f, 1f)
        val scaleY = ObjectAnimator.ofFloat(design.cardViewResult, View.SCALE_Y, 1f, 1.5f, 1f)
        val rotate = ObjectAnimator.ofFloat(design.cardViewResult, View.ROTATION, 0f, 720f)
        val fade = ObjectAnimator.ofFloat(design.cardViewResult, View.ALPHA, 0f, 0.5f, 1f)
        val animatorSet = AnimatorSet().apply {
            playTogether(scaleX, scaleY, rotate,fade)
            duration = 1500
        }
        animatorSet.start()
    }
}