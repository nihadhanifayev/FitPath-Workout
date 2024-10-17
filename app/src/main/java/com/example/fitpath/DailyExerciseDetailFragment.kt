package com.example.fitpath

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.DailyExerciseAdapter
import com.example.fitpath.adapters.ExerciseAdapter
import com.example.fitpath.classes.Exercise
import com.example.fitpath.databinding.FragmentDailyExerciseDetailBinding
import com.example.fitpath.roomDB.dao.DailyExerciseDao
import com.example.fitpath.roomDB.dao.ExerciseDao
import com.example.fitpath.roomDB.database.roomDatabase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DailyExerciseDetailFragment : Fragment() {
    private lateinit var design:FragmentDailyExerciseDetailBinding
    private lateinit var db:roomDatabase
    private lateinit var dailyE_Dao:DailyExerciseDao
    private lateinit var Exercise_Dao:ExerciseDao
    private lateinit var adapter: ExerciseAdapter
    private lateinit var Exercises:ArrayList<Exercise>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentDailyExerciseDetailBinding.inflate(inflater,container,false)
        db = roomDatabase.dataBaseAccess(requireContext())!!
        dailyE_Dao = db.getDailyExerciseDao()
        Exercise_Dao = db.getExerciseDao()
        val bundle:DailyExerciseDetailFragmentArgs by navArgs()
        val exercise = bundle.exercise

        val job2 = CoroutineScope(Dispatchers.Main).launch {
            Exercises = Exercise_Dao.getExercises(exercise.exercise_daily_id) as ArrayList<Exercise>
            if (Exercises.size==0){
            }
            adapter = ExerciseAdapter(requireContext(),Exercises)
            design.DetailExerciseRv.setHasFixedSize(true)
            design.DetailExerciseRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            design.DetailExerciseRv.adapter = adapter
        }
        design.textViewExerciseDetailTitle.text = exercise.title
        design.textViewExerciseDetailTime.text = exercise.time
        design.textViewExerciseDetailDate.text = exercise.date
        design.buttonDeleteExercise.setOnClickListener {
            val job = CoroutineScope(Dispatchers.Main).launch {
                dailyE_Dao.deleteDailyExercise(exercise)
                Navigation.findNavController(it).navigate(R.id.dailyExerciseDetail_DailyExercise)

            }
        }

        return design.root
    }

}