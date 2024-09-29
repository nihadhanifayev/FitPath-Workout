package com.example.fitpath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.WorkoutCategoryAdapter
import com.example.fitpath.classes.WorkoutCategory
import com.example.fitpath.databinding.FragmentWorkoutCategoryBinding
import kotlin.collections.ArrayList


class WorkoutCategoryFragment : Fragment() {
    private lateinit var design:FragmentWorkoutCategoryBinding
    private lateinit var adapter:WorkoutCategoryAdapter
    private lateinit var Categories:ArrayList<WorkoutCategory>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentWorkoutCategoryBinding.inflate(inflater,container,false)

        Categories = ArrayList<WorkoutCategory>()

        val c1 = WorkoutCategory("Chest")
        val c2 = WorkoutCategory("Back")
        val c3 = WorkoutCategory("Biceps")
        val c4 = WorkoutCategory("Triceps")
        val c5 = WorkoutCategory("Leg")
        val c6 = WorkoutCategory("Forearm")
        val c7 = WorkoutCategory("ABS")
        val c8 = WorkoutCategory("Shoulder")

        Categories.add(c1)
        Categories.add(c2)
        Categories.add(c3)
        Categories.add(c4)
        Categories.add(c5)
        Categories.add(c6)
        Categories.add(c7)
        Categories.add(c8)

        adapter = WorkoutCategoryAdapter(requireContext(),Categories)
        design.WorkoutCategoryRv.setHasFixedSize(true)
        design.WorkoutCategoryRv.layoutManager = LinearLayoutManager(requireContext())
        design.WorkoutCategoryRv.adapter = adapter

        return design.root
    }

}