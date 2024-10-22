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
import androidx.navigation.fragment.navArgs
import com.example.fitpath.classes.Program
import com.example.fitpath.databinding.FragmentProgramDetailBinding
import com.example.fitpath.models.ProgramDetailFragmentViewModel
import com.example.fitpath.roomDB.dao.ProgramDao
import com.example.fitpath.roomDB.dao.WorkoutCategoryDao
import com.example.fitpath.roomDB.database.roomDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

@AndroidEntryPoint
class ProgramDetailFragment : Fragment() {
    private lateinit var design:FragmentProgramDetailBinding
    private lateinit var db: roomDatabase
    private lateinit var programDao: ProgramDao
    private lateinit var viewmodel:ProgramDetailFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_program_detail,container,false)
        design.programDetailObject = this
        db = roomDatabase.dataBaseAccess(requireContext())!!
        programDao = db.getProgramDao()
        val bundle:ProgramDetailFragmentArgs by navArgs()
        val program = bundle.program
        if (program != null){
            design.program = program
        }
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:ProgramDetailFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }
    fun createButtonFun(program_title:String,day1:String,day2:String,day3:String,day4:String,day5:String,day6:String,day7:String){
        viewmodel.createProgram(program_title,day1,day2,day3,day4,day5,day6,day7)
        Navigation.findNavController(design.buttonCreateProgram).navigate(R.id.programDetailF_weeklyProgram)
    }

}