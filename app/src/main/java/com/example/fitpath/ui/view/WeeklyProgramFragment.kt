package com.example.fitpath.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.fitpath.R
import com.example.fitpath.ui.adapters.ProgramAdapter
import com.example.fitpath.data.model.Program
import com.example.fitpath.databinding.FragmentWeeklyProgramBinding
import com.example.fitpath.ui.viewmodels.WeeklyProgramFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.ArrayList

@AndroidEntryPoint
class WeeklyProgramFragment : Fragment() {
    private lateinit var design:FragmentWeeklyProgramBinding
    private lateinit var adapter: ProgramAdapter
    private lateinit var programList:ArrayList<Program>
    private val viewmodel: WeeklyProgramFragmentViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_weekly_program,container,false)
        design.weeklyprogramfragmentobject = this
        observeProgramList()
        return design.root
    }
    fun fabClick(){
        val transition = WeeklyProgramFragmentDirections.weeklyProgramFAddProgramF()
        Navigation.findNavController(design.addProgramFab).navigate(transition)
    }
    private fun observeProgramList(){
        viewmodel.livedata.observe(viewLifecycleOwner) { list ->
            programList = ArrayList<Program>()
            programList = list as ArrayList<Program>
            initAdapter(programList)
        }
        viewmodel.getPrograms()
    }
    private fun initAdapter(list:ArrayList<Program>){
        adapter = ProgramAdapter(requireContext(), list, viewmodel)
        design.ProgramsRV.setHasFixedSize(true)
        design.adapter = adapter
    }
}