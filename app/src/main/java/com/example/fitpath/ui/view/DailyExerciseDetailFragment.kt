package com.example.fitpath.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.R
import com.example.fitpath.ui.adapters.ExerciseAdapter
import com.example.fitpath.data.model.DailyExercise
import com.example.fitpath.data.model.Exercise
import com.example.fitpath.databinding.FragmentDailyExerciseDetailBinding
import com.example.fitpath.ui.viewmodels.DailyExerciseDetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyExerciseDetailFragment : Fragment() {
    private lateinit var design:FragmentDailyExerciseDetailBinding
    private lateinit var adapter: ExerciseAdapter
    private lateinit var Exercises:ArrayList<Exercise>
    private val viewmodel: DailyExerciseDetailFragmentViewModel by viewModels()
    private lateinit var exercise: DailyExercise
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_daily_exercise_detail,container,false)
        design.dailyexercisedetailfragmentobject = this
        val bundle:DailyExerciseDetailFragmentArgs by navArgs()
        exercise = bundle.exercise
        design.exercise = exercise
        observeExercise()
        return design.root
    }
    fun deleteButton(){
        viewmodel.deleteDailyExercise(exercise)
        Navigation.findNavController(design.buttonDeleteExercise).navigate(R.id.dailyExerciseDetail_DailyExercise)
    }
    private fun observeExercise(){
        viewmodel.getExercise(exercise.exerciseDailyId)
        viewmodel.livedata.observe(viewLifecycleOwner,{list->
            Exercises = list as ArrayList<Exercise>
            initAdapter(Exercises)
        })
    }
    private fun initAdapter(exercises:ArrayList<Exercise>){
        adapter = ExerciseAdapter(requireContext(),exercises)
        design.DetailExerciseRv.setHasFixedSize(true)
        design.DetailExerciseRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        design.adapter = adapter
    }


}