package com.example.fitpath

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpath.adapters.ProgramAdapter
import com.example.fitpath.classes.Program
import com.example.fitpath.databinding.FragmentWeeklyProgramBinding
import com.example.fitpath.models.WeeklyProgramFragmentViewModel
import com.example.fitpath.roomDB.dao.ProgramDao
import com.example.fitpath.roomDB.database.roomDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

@AndroidEntryPoint
class WeeklyProgramFragment : Fragment() {
    private lateinit var design:FragmentWeeklyProgramBinding
    private lateinit var adapter:ProgramAdapter
    private lateinit var Program_List:ArrayList<Program>
    private lateinit var viewmodel:WeeklyProgramFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_weekly_program,container,false)
        design.weeklyprogramfragmentobject = this
        viewmodel.getPrograms()
        viewmodel.livedata.observe(viewLifecycleOwner,{list->
            Program_List = ArrayList<Program>()
            Program_List = list as ArrayList<Program>
            adapter = ProgramAdapter(requireContext(),Program_List,viewmodel)
            design.ProgramsRV.setHasFixedSize(true)
            design.adapter = adapter
        })
        return design.root
    }
    fun fabClick(){
        val transition = WeeklyProgramFragmentDirections.weeklyProgramFAddProgramF()
        Navigation.findNavController(design.addProgramFab).navigate(transition)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:WeeklyProgramFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }

}