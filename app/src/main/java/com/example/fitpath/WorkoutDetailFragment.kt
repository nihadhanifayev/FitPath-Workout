package com.example.fitpath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.fitpath.databinding.FragmentWorkoutDetailBinding


class WorkoutDetailFragment : Fragment() {
    private lateinit var design:FragmentWorkoutDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentWorkoutDetailBinding.inflate(inflater,container,false)
        val bundle:WorkoutDetailFragmentArgs by navArgs()
        val workout = bundle.workout

        design.textViewWorkoutDetailName.text = workout.workout
        design.textViewWorkoutDetailInfo.text = workout.workoutInfo
        design.imageViewWorkoutDetail.setImageResource((activity as AppCompatActivity).resources.getIdentifier(workout.workoutImage,"drawable",
            (activity as AppCompatActivity).packageName))

        return design.root
    }

}