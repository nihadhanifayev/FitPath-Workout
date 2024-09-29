package com.example.fitpath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.fitpath.databinding.FragmentDailyExerciseBinding


class DailyExerciseFragment : Fragment() {
    private lateinit var design:FragmentDailyExerciseBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentDailyExerciseBinding.inflate(inflater,container,false)

        design.buttonAddExercise.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.dailyExerciseFragment_exerciseFragment)
        }

        return design.root

    }

}