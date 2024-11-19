package com.example.fitpath

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
import com.example.fitpath.adapters.ChildRecyclerViewAdapter
import com.example.fitpath.classes.ChildItem
import com.example.fitpath.classes.Workout
import com.example.fitpath.databinding.FragmentWorkoutDetailBinding
import com.example.fitpath.models.WorkoutDetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutDetailFragment : Fragment() {
    private lateinit var design:FragmentWorkoutDetailBinding
    private lateinit var viewmodel:WorkoutDetailFragmentViewModel
    private lateinit var adapter:ChildRecyclerViewAdapter
    private lateinit var ListWorkoutItem:ArrayList<ChildItem>
    private lateinit var ListWorkout:ArrayList<Workout>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_workout_detail,container,false)
        val bundle:WorkoutDetailFragmentArgs by navArgs()
        val workout = bundle.workout
        design.workout = workout
        ListWorkoutItem = ArrayList<ChildItem>()
        design.imageViewWorkoutDetail.setImageResource((activity as AppCompatActivity).
        resources.getIdentifier(workout.workoutImage,"drawable",(activity as AppCompatActivity).packageName))
        design.rvAlternatives.setHasFixedSize(true)
        design.rvAlternatives.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        viewmodel.workoutLiveData.observe(viewLifecycleOwner){listWorkout->
            ListWorkout = listWorkout as ArrayList<Workout>
            for (childItem in ListWorkout){
                val item = ChildItem(childItem.workout,childItem.workoutImage)
                ListWorkoutItem.add(item)
                adapter = ChildRecyclerViewAdapter(requireContext(),ListWorkoutItem)
                design.adapter = adapter
            }
        }
        viewmodel.getCategories(workout.workoutCategory)
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:WorkoutDetailFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }

}