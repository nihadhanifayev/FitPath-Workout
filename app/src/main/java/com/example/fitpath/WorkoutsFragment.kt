package com.example.fitpath

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.WorkoutsAdapter
import com.example.fitpath.classes.Workout
import com.example.fitpath.databinding.FragmentWorkoutsBinding
import com.example.fitpath.roomDB.dao.WorkoutDao
import com.example.fitpath.roomDB.database.roomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch

class WorkoutsFragment : Fragment() {
    private lateinit var design:FragmentWorkoutsBinding
    private lateinit var adapter:WorkoutsAdapter
    private lateinit var workouts:ArrayList<Workout>
    private lateinit var db:roomDatabase
    private lateinit var workoutDao:WorkoutDao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentWorkoutsBinding.inflate(inflater,container,false)
        val bundle:WorkoutsFragmentArgs by navArgs()
        val category = bundle.category
        db = roomDatabase.dataBaseAccess(requireContext())!!
        workoutDao = db.getWorkoutDao()
        workouts = ArrayList<Workout>()
        val job = CoroutineScope(Dispatchers.Main).launch {
            workouts = workoutDao.getWorkouts(category) as ArrayList<Workout>
            adapter = WorkoutsAdapter(requireContext(),workouts)
            design.WorkoutRV.setHasFixedSize(true)
            design.WorkoutRV.layoutManager = LinearLayoutManager(requireContext())
            design.WorkoutRV.adapter = adapter
        }
        return design.root
    }


}