package com.example.fitpath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.fitpath.classes.Program
import com.example.fitpath.databinding.FragmentProgramDetailBinding
import com.example.fitpath.roomDB.dao.ProgramDao
import com.example.fitpath.roomDB.dao.WorkoutCategoryDao
import com.example.fitpath.roomDB.database.roomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class ProgramDetailFragment : Fragment() {
    private lateinit var design:FragmentProgramDetailBinding
    private lateinit var db: roomDatabase
    private lateinit var programDao: ProgramDao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentProgramDetailBinding.inflate(inflater,container,false)
        db = roomDatabase.dataBaseAccess(requireContext())!!
        programDao = db.getProgramDao()
        val bundle:ProgramDetailFragmentArgs by navArgs()
        val program = bundle.program
        if (program != null){
            design.textProgramTitle.setText(program?.program_name)
            design.textOne.setText(program?.dayOne)
            design.textTwo.setText(program?.dayTwo)
            design.textThree.setText(program?.dayThree)
            design.textFour.setText(program?.dayFour)
            design.textFife.setText(program?.dayFife)
            design.textSix.setText(program?.daySix)
            design.textSeven.setText(program?.daySeven)
        }
        design.buttonCreateProgram.setOnClickListener {

            val program_name = design.textProgramTitle.text.toString()
            val day1 = design.textOne.text.toString()
            val day2 = design.textTwo.text.toString()
            val day3 = design.textThree.text.toString()
            val day4 = design.textFour.text.toString()
            val day5 = design.textFife.text.toString()
            val day6 = design.textSix.text.toString()
            val day7 = design.textSeven.text.toString()

            val program1 = Program(0,program_name,day1,day2,day3,day4,day5,day6,day7)
            val job = CoroutineScope(Dispatchers.Main).launch {
                programDao.addProgram(program1)
            }
            Navigation.findNavController(it).navigate(R.id.programDetailF_weeklyProgram)
        }

        return design.root
    }

}