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
import com.example.fitpath.roomDB.dao.ProgramDao
import com.example.fitpath.roomDB.database.roomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class WeeklyProgramFragment : Fragment() {
    private lateinit var design:FragmentWeeklyProgramBinding
    private lateinit var adapter:ProgramAdapter
    private lateinit var Program_List:ArrayList<Program>
    private lateinit var db: roomDatabase
    private lateinit var programDao:ProgramDao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentWeeklyProgramBinding.inflate(inflater,container,false)
        db = roomDatabase.dataBaseAccess(requireContext())!!
        programDao = db.getProgramDao()

        val job = CoroutineScope(Dispatchers.Main).launch {
            Program_List = ArrayList<Program>()
            Program_List = programDao.getPrograms() as ArrayList<Program>
            adapter = ProgramAdapter(requireContext(),Program_List)
            design.ProgramsRV.layoutManager = LinearLayoutManager(requireContext())
            design.ProgramsRV.setHasFixedSize(true)
            design.ProgramsRV.adapter = adapter
        }
        design.addProgramFab.setOnClickListener {
            val transition = WeeklyProgramFragmentDirections.weeklyProgramFAddProgramF()
            Navigation.findNavController(it).navigate(transition)
        }

        return design.root
    }

}