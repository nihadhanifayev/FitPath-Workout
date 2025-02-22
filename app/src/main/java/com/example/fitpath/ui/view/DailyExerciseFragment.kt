package com.example.fitpath.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.fitpath.R
import com.example.fitpath.ui.adapters.DailyExerciseAdapter
import com.example.fitpath.data.model.DailyExercise
import com.example.fitpath.databinding.FragmentDailyExerciseBinding
import com.example.fitpath.ui.viewmodels.DailyExerciseFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyExerciseFragment : Fragment() {
    private lateinit var design:FragmentDailyExerciseBinding
    private lateinit var dailyExercises:ArrayList<DailyExercise>
    private lateinit var adapter: DailyExerciseAdapter
    private val viewmodel: DailyExerciseFragmentViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_daily_exercise,container,false)
        observeDailyExercise()
        return design.root
    }
    private fun observeDailyExercise(){
        viewmodel.livedata.observe(viewLifecycleOwner,{list->
            dailyExercises = list as ArrayList<DailyExercise>
            initAdapter(dailyExercises)
        })
        viewmodel.getDailyExercises()
    }
    private fun initAdapter(dailyExercises: ArrayList<DailyExercise>){
        adapter = DailyExerciseAdapter(requireContext(),dailyExercises)
        design.DailyExerciseRv.setHasFixedSize(true)
        design.adapter = adapter
    }
}