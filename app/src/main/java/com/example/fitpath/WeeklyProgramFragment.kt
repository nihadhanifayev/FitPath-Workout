package com.example.fitpath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.ProgramAdapter
import com.example.fitpath.classes.Program
import com.example.fitpath.databinding.FragmentWeeklyProgramBinding
import kotlin.collections.ArrayList


class WeeklyProgramFragment : Fragment() {
    private lateinit var design:FragmentWeeklyProgramBinding
    private lateinit var adapter:ProgramAdapter
    private lateinit var Program_List:ArrayList<Program>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentWeeklyProgramBinding.inflate(inflater,container,false)

        Program_List = ArrayList<Program>()
        adapter = ProgramAdapter(requireContext(),Program_List)
        design.ProgramsRV.layoutManager = LinearLayoutManager(requireContext())
        design.ProgramsRV.setHasFixedSize(true)
        design.ProgramsRV.adapter = adapter

        design.addProgramFab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.weeklyProgramFragment_programDetailFragment)
        }

        return inflater.inflate(R.layout.fragment_weekly_program, container, false) }

}