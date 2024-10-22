package com.example.fitpath

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.DailyExerciseAdapter
import com.example.fitpath.adapters.ExerciseAdapter
import com.example.fitpath.classes.DailyExercise
import com.example.fitpath.classes.Exercise
import com.example.fitpath.databinding.FragmentDailyExerciseDetailBinding
import com.example.fitpath.models.DailyExerciseDetailFragmentViewModel
import com.example.fitpath.roomDB.dao.DailyExerciseDao
import com.example.fitpath.roomDB.dao.ExerciseDao
import com.example.fitpath.roomDB.database.roomDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DailyExerciseDetailFragment : Fragment() {
    private lateinit var design:FragmentDailyExerciseDetailBinding
    private lateinit var adapter: ExerciseAdapter
    private lateinit var Exercises:ArrayList<Exercise>
    private lateinit var viewmodel:DailyExerciseDetailFragmentViewModel
    private lateinit var exercise:DailyExercise
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_daily_exercise_detail,container,false)
        design.dailyexercisedetailfragmentobject = this
        val bundle:DailyExerciseDetailFragmentArgs by navArgs()
        exercise = bundle.exercise
        design.exercise = exercise
        viewmodel.getExercise(exercise.exercise_daily_id)
        viewmodel.livedata.observe(viewLifecycleOwner,{list->
            Exercises = list as ArrayList<Exercise>
            adapter = ExerciseAdapter(requireContext(),Exercises)
            design.DetailExerciseRv.setHasFixedSize(true)
            design.DetailExerciseRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            design.adapter = adapter
        })


        return design.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DailyExerciseDetailFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }
    fun deleteButton(){
        viewmodel.deleteDailyExercise(exercise)
        Navigation.findNavController(design.buttonDeleteExercise).navigate(R.id.dailyExerciseDetail_DailyExercise)
    }


}