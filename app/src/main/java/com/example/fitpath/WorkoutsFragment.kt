package com.example.fitpath

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.WorkoutsAdapter
import com.example.fitpath.classes.Workout
import com.example.fitpath.databinding.FragmentWorkoutsBinding
import kotlinx.coroutines.currentCoroutineContext

class WorkoutsFragment : Fragment() {
    private lateinit var design:FragmentWorkoutsBinding
    private lateinit var adapter:WorkoutsAdapter
    private lateinit var workouts:ArrayList<Workout>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentWorkoutsBinding.inflate(inflater,container,false)



        adapter = WorkoutsAdapter(requireContext(),workouts)
        design.WorkoutRV.setHasFixedSize(true)
        design.WorkoutRV.layoutManager = LinearLayoutManager(requireContext())
        design.WorkoutRV.adapter = adapter
        return design.root
    }


}