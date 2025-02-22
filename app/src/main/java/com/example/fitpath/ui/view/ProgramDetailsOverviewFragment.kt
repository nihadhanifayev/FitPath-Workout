package com.example.fitpath.ui.view

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.R
import com.example.fitpath.ui.adapters.ParentRecyclerViewAdapter
import com.example.fitpath.data.model.ChildItem
import com.example.fitpath.data.model.ParentItem
import com.example.fitpath.data.model.Program
import com.example.fitpath.data.model.ProgramWorkouts
import com.example.fitpath.databinding.FragmentProgramDetailsOverviewBinding
import com.example.fitpath.ui.viewmodels.ProgramDetailsOverviewFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class ProgramDetailsOverviewFragment : Fragment() {
    private lateinit var design:FragmentProgramDetailsOverviewBinding
    private val viewmodel: ProgramDetailsOverviewFragmentViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,
            R.layout.fragment_program_details_overview,container,false)
        val bundle:ProgramDetailsOverviewFragmentArgs by navArgs()
        val program = bundle.program
        design.program = program
        getProgramWorkoutsAndCreateChildItem(program)
        viewmodel.parentItemCollection(program)
        initAdapter()
        return design.root
    }
    private fun getProgramWorkoutsAndCreateChildItem(program: Program){
        for (x in 1..7){
            lifecycleScope.launch {
                viewmodel.getProgramWorkoutsWithId(program.programId,x)
                selectWorkoutImgAndCreateChildItem(viewmodel.list,x)
            }
        }
    }
    private fun selectWorkoutImgAndCreateChildItem(list: List<ProgramWorkouts>, x: Int){
        for (item in 0..list.size-1){
            var workoutImg = ""
            val splitList = list[item].workoutName.lowercase(Locale.ROOT).replace(" ","_")
            for (y in splitList){
                workoutImg+=y
            }
            val childItem = ChildItem(list[item].workoutName,workoutImg)

            viewmodel.childListManager[x-1].add(childItem)
        }
    }
    private fun initAdapter(){
        design.rvProgramWorkoutsDetail.adapter = ParentRecyclerViewAdapter(requireContext(),viewmodel.programArray)
        design.rvProgramWorkoutsDetail.setHasFixedSize(true)
        design.rvProgramWorkoutsDetail.layoutManager = LinearLayoutManager(requireContext())
    }
}