package com.example.fitpath

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.ParentRecyclerViewAdapter
import com.example.fitpath.classes.ChildItem
import com.example.fitpath.classes.ParentItem
import com.example.fitpath.databinding.FragmentProgramDetailsOverviewBinding
import com.example.fitpath.models.ProgramDetailsOverviewFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgramDetailsOverviewFragment : Fragment() {
    private lateinit var design:FragmentProgramDetailsOverviewBinding
    private lateinit var viewmodel:ProgramDetailsOverviewFragmentViewModel
    private lateinit var ProgramArray:ArrayList<ParentItem>
    private lateinit var ChildList1:ArrayList<ChildItem>
    private lateinit var ChildList2:ArrayList<ChildItem>
    private lateinit var ChildList3:ArrayList<ChildItem>
    private lateinit var ChildList4:ArrayList<ChildItem>
    private lateinit var ChildList5:ArrayList<ChildItem>
    private lateinit var ChildList6:ArrayList<ChildItem>
    private lateinit var ChildList7:ArrayList<ChildItem>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_program_details_overview,container,false)
        val bundle:ProgramDetailsOverviewFragmentArgs by navArgs()
        val program = bundle.program
        design.program = program
        ChildList1 = ArrayList<ChildItem>()
        ChildList2 = ArrayList<ChildItem>()
        ChildList3 = ArrayList<ChildItem>()
        ChildList4 = ArrayList<ChildItem>()
        ChildList5 = ArrayList<ChildItem>()
        ChildList6 = ArrayList<ChildItem>()
        ChildList7 = ArrayList<ChildItem>()
        viewmodel.getProgramWorkoutsWithIdOne(program.program_id,1)
        viewmodel.liveDataProgramWorkoutsone.observe(viewLifecycleOwner,{list->
            for (x in 0..list.size-1){
                var workout_img = ""
                val splittedList = list[x].workout_name.toLowerCase().replace(" ","_")
                for (y in splittedList){
                    workout_img+=y
                }
                val childItem = ChildItem(list[x].workout_name,workout_img)
                ChildList1.add(childItem)
            }
        })
        viewmodel.getProgramWorkoutsWithIdTwo(program.program_id,2)
        viewmodel.liveDataProgramWorkoutstwo.observe(viewLifecycleOwner,{list->
            for (x in 0..list.size-1){
                var workout_img = ""
                val splittedList = list[x].workout_name.toLowerCase().replace(" ","_")
                for (y in splittedList){
                    workout_img+=y
                }
                val childItem = ChildItem(list[x].workout_name,workout_img)
                ChildList2.add(childItem)
            }
        })
        viewmodel.getProgramWorkoutsWithIdThree(program.program_id,3)
        viewmodel.liveDataProgramWorkoutsthree.observe(viewLifecycleOwner,{list->
            for (x in 0..list.size-1){
                var workout_img = ""
                val splittedList = list[x].workout_name.toLowerCase().replace(" ","_")
                for (y in splittedList){
                    workout_img+=y
                }
                val childItem = ChildItem(list[x].workout_name,workout_img)
                ChildList3.add(childItem)
            }
        })
        viewmodel.getProgramWorkoutsWithIdFour(program.program_id,4)
        viewmodel.liveDataProgramWorkoutsfour.observe(viewLifecycleOwner,{list->
            for (x in 0..list.size-1){
                var workout_img = ""
                val splittedList = list[x].workout_name.toLowerCase().replace(" ","_")
                for (y in splittedList){
                    workout_img+=y
                }
                val childItem = ChildItem(list[x].workout_name,workout_img)
                ChildList4.add(childItem)
            }
        })
        viewmodel.getProgramWorkoutsWithIdFife(program.program_id,5)
        viewmodel.liveDataProgramWorkoutsfife.observe(viewLifecycleOwner,{list->
            for (x in 0..list.size-1){
                var workout_img = ""
                val splittedList = list[x].workout_name.toLowerCase().replace(" ","_")
                for (y in splittedList){
                    workout_img+=y
                }
                val childItem = ChildItem(list[x].workout_name,workout_img)
                ChildList5.add(childItem)
            }
        })
        viewmodel.getProgramWorkoutsWithIdSix(program.program_id,6)
        viewmodel.liveDataProgramWorkoutssix.observe(viewLifecycleOwner,{list->
            for (x in 0..list.size-1){
                var workout_img = ""
                val splittedList = list[x].workout_name.toLowerCase().replace(" ","_")
                for (y in splittedList){
                    workout_img+=y
                }
                val childItem = ChildItem(list[x].workout_name,workout_img)
                ChildList6.add(childItem)
            }
        })
        viewmodel.getProgramWorkoutsWithIdSeven(program.program_id,7)
        viewmodel.liveDataProgramWorkoutsseven.observe(viewLifecycleOwner,{list->
            for (x in 0..list.size-1){
                var workout_img = ""
                val splittedList = list[x].workout_name.toLowerCase().replace(" ","_")
                for (y in splittedList){
                    workout_img+=y
                }
                val childItem = ChildItem(list[x].workout_name,workout_img)
                ChildList7.add(childItem)
            }
        })

        ProgramArray = ArrayList<ParentItem>()
        val parentItem1 = ParentItem(program.dayOne,ChildList1,false)
        val parentItem2 = ParentItem(program.dayTwo,ChildList2,false)
        val parentItem3 = ParentItem(program.dayThree,ChildList3,false)
        val parentItem4 = ParentItem(program.dayFour,ChildList4,false)
        val parentItem5 = ParentItem(program.dayFife,ChildList5,false)
        val parentItem6 = ParentItem(program.daySix,ChildList6,false)
        val parentItem7 = ParentItem(program.daySeven,ChildList7,false)
        ProgramArray.add(parentItem1)
        ProgramArray.add(parentItem2)
        ProgramArray.add(parentItem3)
        ProgramArray.add(parentItem4)
        ProgramArray.add(parentItem5)
        ProgramArray.add(parentItem6)
        ProgramArray.add(parentItem7)

        design.rvProgramWorkoutsDetail.adapter = ParentRecyclerViewAdapter(requireContext(),ProgramArray)
        design.rvProgramWorkoutsDetail.setHasFixedSize(true)
        design.rvProgramWorkoutsDetail.layoutManager = LinearLayoutManager(requireContext())

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:ProgramDetailsOverviewFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }
}