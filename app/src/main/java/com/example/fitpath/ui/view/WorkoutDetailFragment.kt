package com.example.fitpath.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.R
import com.example.fitpath.ui.adapters.ChildRecyclerViewAdapter
import com.example.fitpath.data.model.ChildItem
import com.example.fitpath.data.model.Workout
import com.example.fitpath.databinding.FragmentWorkoutDetailBinding
import com.example.fitpath.ui.viewmodels.WorkoutDetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutDetailFragment : Fragment() {
    private lateinit var design:FragmentWorkoutDetailBinding
    private val viewmodel: WorkoutDetailFragmentViewModel by viewModels()
    private lateinit var adapter: ChildRecyclerViewAdapter
    private lateinit var listWorkoutItem:ArrayList<ChildItem>
    private lateinit var listWorkout:ArrayList<Workout>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_workout_detail,container,false)
        val bundle:WorkoutDetailFragmentArgs by navArgs()
        val workout = bundle.workout

        design.workout = workout
        listWorkoutItem = ArrayList<ChildItem>()

        design.imageViewWorkoutDetail.setImageResource((activity as AppCompatActivity).
        resources.getIdentifier(workout.workoutImage,"drawable",(activity as AppCompatActivity).packageName))
        design.rvAlternatives.setHasFixedSize(true)
        design.rvAlternatives.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        viewmodel.workoutLiveData.observe(viewLifecycleOwner){listWorkout->
            this.listWorkout = listWorkout as ArrayList<Workout>
            for (childItem in this.listWorkout){
                val item = ChildItem(childItem.workout,childItem.workoutImage)
                listWorkoutItem.add(item)
                adapter = ChildRecyclerViewAdapter(requireContext(),listWorkoutItem)
                design.adapter = adapter
            }
        }
        viewmodel.getCategories(workout.workoutCategory)
        return design.root
    }
}