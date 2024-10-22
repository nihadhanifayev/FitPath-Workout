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
import com.example.fitpath.databinding.FragmentWorkoutDetailBinding
import com.example.fitpath.models.WorkoutDetailFragmentViewModel


class WorkoutDetailFragment : Fragment() {
    private lateinit var design:FragmentWorkoutDetailBinding
    private lateinit var viewmodel:WorkoutDetailFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_workout_detail,container,false)
        val bundle:WorkoutDetailFragmentArgs by navArgs()
        val workout = bundle.workout
        design.workout = workout
        design.imageViewWorkoutDetail.setImageResource((activity as AppCompatActivity).
        resources.getIdentifier(workout.workoutImage,"drawable",(activity as AppCompatActivity).packageName))
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:WorkoutDetailFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }

}